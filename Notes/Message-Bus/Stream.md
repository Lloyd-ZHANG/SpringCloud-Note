# Stream

Spring Cloud Stream是一个构建消息驱动微服务的框架。

应用程序通过申请inputs或outputs信道与Spring Cloud Stream中的绑定对象（binder）交互，而Spring Cloud Stream的Binder则负责与消息中间件交互，通过这种方法实现应用程序与消息中间件之间的隔离，我们只需搞清楚如何与Spring Cloud Stream交互就可以方便的使用消息驱动。

通过使用Spring Integration来连接消息代理中间件以实现消息事件驱动。Spring Cloud Stream为一些供应商的消息中间件提供了个性化的自动化配置实现，引用了发布订阅、消费组、分区的三个核心概念

目前Spring Cloud Stream只支持RabbitMQ、Kafka，下面采用RabbitMQ做笔记

## 基本实现原理

在没有binder概念时，SpringBoot应用需要直接与消息中间件进行消息交互，此时由于各消息中间件的构建初衷有所不同，他们的实现细节上也会有较大差异

Spring Cloud Stream通过定义Binder作为中间层，完美地实现了应用程序与消息中间件细节之间的隔离，使得应用程序不再需要考虑各种不同消息中间件的实现。

![SpringCloudStream](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Message-Bus/img/SpringCloudStream.png)

binder的出现也让应用程序对接至不同消息中间件的难度大大减少：

![UsingDifferentMiddleware](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Message-Bus/img/UsingDifferentMiddleware.png)

binder中，output对应生产者，input对应消费者，消息通信的方式遵循发布订阅模式，可以通过设定组（group）的方式指定消息的流向

## 生产者配置

### 方法一

该方法仍可用，但已不再被推荐

1. yml配置

```yml
spring:
    application:
      name: cloud-stream-provider
    cloud:
      stream:
        binders:
          defaultRabbit:  # 配置要绑定的rabbitmq的服务信息，这里是服务名，可以是defaultRabbit，也可以是Rabbit1或者Rabbit2
            type: rabbit  # 消息组件类型
            environment:  # 设置rabbitmq的相关环境配置，内容与application.yml中的spring.rabbitmq一致
              spring:
                rabbitmq:
                  host: localhost
                  port: 5672
                  username: puuaru
                  password: password
        bindings:
          output: # 声明binding名
            destination: studyExchange # 声明要使用的Exchange名
            content-type: application/json # 消息类型，文本为text/plain
            binder: defaultRabbit # 指定要绑定的消息服务
        # rabbit:
        #   bindings:
        # 这里可以为RabbitMQ针对每个binding配置属性，如死信队列相关配置(ttl、autoBindDlq、deadLetterExchange等)
```

2. 编写业务类

```java
/**
 * @Description: 消息生产者
 * @Author: puuaru
 * @Date: 2022/10/3
 */
@EnableBinding(Source.class)    // 指定该服务之于Binding的类型(Source还是Sink)
@Slf4j
public class MessageProviderImpl implements MessageProvider {
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String uuid = IdUtil.simpleUUID();
        output.send(MessageBuilder.withPayload(uuid).build());
        log.info("********* send message: " + uuid);
        return "SUCCESS";
    }
}
```

### 方法二

目前官方更推荐的方法

1. yml配置

```yml
spring:
    application:
      name: cloud-stream-provider
    cloud:
      stream:
        binders:
          defaultRabbit:  # 配置要绑定的rabbitmq的服务信息，这里是服务名，可以是defaultRabbit，也可以是Rabbit1或者Rabbit2
            type: rabbit  # 消息组件类型
            environment:  # 设置rabbitmq的相关环境配置，内容与application.yml中的spring.rabbitmq一致
              spring:
                rabbitmq:
                  host: localhost
                  port: 5672
                  username: puuaru
                  password: password
        bindings:
          streamTest-out-0: # 声明binding名，与方法一最大的区别，这里的方法名有如下格式要求:
          # streamTest为自定义的绑定名称，out代表生产者，0是该binding的index
          # 多数情况下生产者/消费者一次只会产生/消费一条消息，因此index多使用0
          # 若生产者一次生产多条信息，或是消费者一次需要消费多条消息，则需用0以上的index
            destination: studyExchange # 声明要使用的Exchange名
            content-type: application/json # 消息类型，文本为text/plain
            binder: defaultRabbit # 指定要绑定的消息服务
        # rabbit:
        #   bindings:
        # 这里可以为RabbitMQ针对每个binding配置属性，如死信队列相关配置(ttl、autoBindDlq、deadLetterExchange等)
```

2. 编写业务类

```java
/**
 * @Description: 消息生产者
 * @Author: puuaru
 * @Date: 2022/10/3
 */
// @EnableBinding(Source.class)    // 该方法下无需使用这个注解
@Slf4j
public class MessageProviderImpl implements MessageProvider {
    // @Autowired
    // private MessageChannel output;
    @Autowired
    private StreamBridge streamBridge;

    @Override
    public String send() {
        String uuid = IdUtil.simpleUUID();
        streamBridge.send("streamTest-out-0", MessageBuilder.withPayload(uuid).build());
        log.info("********* send message: " + uuid);
        return "SUCCESS";
    }
}
```

## 消费者配置

与生产者相似，方法一、方法二相互对应

### 方法一

1. yml配置，把生产者的output改为input即可
2. 编写业务类

```java
/**
 * @Description: 消费者Controller
 * @Author: puuaru
 * @Date: 2022/10/3
 */
@EnableBinding(Sink.class)
@Slf4j
public class ReceiveMessageController {
    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        log.info("********* consumer: " + port + " received message: " + message.getPayload());
    }
}
```

### 方法二

1. yml配置，把生产者的out改为in即可
2. 编写返回 `Consumer<T>` 的消费Bean

```java
@Component
public class MessageConsumer {
    @Bean
    public Consumer<String> streamTest() {  // 方法名必须与生产消息时自定义的binding一致
        return message -> {
            log.info("received message: " + message.getPayload());
        };
    }
}
```

## 消费者分组

### 消息竞争

Stream为发布订阅模式，因此会发生如下情况: 假如订单系统做了集群部署，都会从RabbitMQ中获取消息，但因为发布订阅模式，不作任何改动的情况下同一个订单会被两个服务获取到，从而造成数据错误，我们要避免这种情况。

这时可以使用Stream中的消息分组解决，Stream中同一个组中的多个消费者是竞争关系，能够保证消息只会被其中一个应用消费一次；而不同组可以全面消费（重复消费）。

实际上，可以发现分组之后的消息分发采用轮询模式。

分组的本质是分队列，为集群分组相当于在集群内采取work模式，具体到单个队列来讲就是为队列声明禁止消息共享。

### 消息持久化

此外，为消费者分组之后也能让MQ自动支持持久化，避免服务宕机时发生消息丢失的情况

### 配置

```yml
spring:
    application:
      name: cloud-stream-consumer
    cloud:
      stream:
        bindings: # 服务整合的处理
          input: # 信道的名称
            group: group1
```

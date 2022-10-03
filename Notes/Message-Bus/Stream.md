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

# Gateway

## 简介

SpringCloud Gateway是在Spring生态系统之上构建的API网关服务，基于Spring5、SpringBoot2、ProjectReactor等技术，旨在提供一种简单有效的方式来对API进行路由，以及提供一些强大的过滤器功能，如熔断、限流、重试等

Gateway作为SpringCloud生态系统的网关，目标是替代Zuul，在SpringCloud2.0以上的版本中不再对Zuul2.0以上的最新高性能版本进行继承，而仍然使用Zuul1.x非Reactor模式的老版本。为了提升网关的性能，**SpringCloud Gateway基于WebFlux框架实现，而WebFlux框架底层则使用了高性能的Reactor模式通信框架Netty**

## 构成

Gateway的路由是一种动态路由，web请求通过一些匹配条件，定位到真正的服务节点，并在这个转发过程的前后，进行一些精细化控制

predicate（断言）就是匹配条件，而filter是一拦截器，上述两个元素再加上目标URI，就可以实现一个具体的路由

### Route 路由

构建网关的基本模块，由ID、目标URI、一系列的断言和过滤器组成，如果断言为true则匹配该路由

### Predicate 断言

匹配http中的所有内容（请求头，请求参数等），如果请求与断言相匹配则进行路由

### Filter 过滤器

Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或者后对请求进行修改

## 流程

客户端向SpringCloud Gateway发出请求，然后在Gateway Handler Mapping中找到与请求匹配的路由，将其发送到Gateway Web Handler

Handler再通过指定的过滤器链来将请求发送到实际的服务执行业务逻辑然后返回。过滤器可能在发送代理请求前后（pre or post）执行一定的业务逻辑。

Filter在pre类型的过滤器可以做参数检验、权限校验、流量监控、日志输出、协议转换等；在post类型的过滤器中可以做响应内容、响应头的修改、日志输出、流量监控等，有着非常重要的作用

## 配置

### 静态路由

```yml
server:
  port: 9527
spring:
  application:
    name: cloud-gateway-service
  cloud:
    gateway:
      routes:
        - id: payment_route           # 路由名，没有固定规则，但要求唯一，建议配合服务名
          uri: <http://localhost:8001>  # 匹配后提供服务的路由地址
          predicates:
            - Path=/payment/**        # 断言，路径相匹配的进行路由
```

### 动态路由

> 默认情况下Gateway会根据注册中心注册的服务列表，以注册中心上的微服务名为路径创建动态路由进行转发

```yml
spring:
    cloud:
      gateway:
        discovery:
          locator:
            enabled: true   #开启动态路由，Gateway会从注册中心动态创建路由，利用微服务名进行路由
        routes:
          - id: payment_routh           # 路由的id，没有固定规则，但要求唯一，建议配合服务名
            uri: lb://CLOUD-PAYMENT-SERVICE  # 匹配后提供服务的路由地址
            predicates:
              - Path=/payment/**        # 断言，路径相匹配的进行路由
```

## 配置断言Predicate

可参考[官方文档](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-request-predicates-factories)，写的很清晰，大致情况可以分为：根据访问时间、用户Cookie、请求报文各类信息（请求头所带属性、Host、METHOD、RemoteAddr、参数...）

## 配置过滤器Filter

可参考官方文档：

* [局部](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gatewayfilter-factories)
* [全局](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#global-filters)

## 自定义全局过滤器

自定义过滤器需要实现接口 `GlobalFilter`、`Ordered`，然后重写filter()方法来实现过滤逻辑，并在getOrder()方法中指定过滤器在过滤器链中的执行优先级，Order值越小越优先；pre-filter的逻辑可在 `chain.filter(exchange)` 前完成，post-filter的逻辑则通过 `chain.filter(exchange).then(Mono.fromRunnable(() -> {//逻辑}))` 完成， `fromRunnabler()` 方法接受一个 `Runnable` 作为参数并运行 `Runnable` 内编写的逻辑，最终返回一个 `Mono`

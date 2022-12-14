# 服务注册中心

## 组件

1. [Eureka](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Registry/Eureka-Note.md)
2. [Consul](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Registry/Consul-Note.md)
3. [Zookeeper](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Registry/Zookeeper-Note.md)

## 服务治理

在传统的RPC远程调用框架中，管理每个服务与服务之间的依赖关系比较复杂，所以需要使用服务治理，管理服务与服务之间的依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册

Spring Cloud封装了Netflix公司开发的Eureka来实现服务治理

## 服务注册与发现

系统中的微服务，通过**服务注册客户端**连接到服务器并维持心跳连接，这样系统的维护人员就可以通过服务注册服务器来监控各个微服务是否正常运行。

在服务注册与发现中，有一个注册中心，当服务器启动时，会把当前服务器的信息（如服务地址通讯地址等以别名的方式注册到注册中心上）。消费者/服务提供者通过该别名的方式去注册中心上获取到实际的服务通讯地址，然后再实现本地RPC调用RPC远程调用框架。其核心设计思想在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系（服务治理）。

在任何RPC远程框架中都会有一个注册中心。

## Eureka, Consul, Zookeeper的异同

| 组件名 | 语言 | CAP | 服务健康检查 | 对外暴露接口 |
| :----: | :--: | :--: | :--------:| :----------:|
| Eureka | Java | AP |    可支持    |    HTTP    |
| Consul | Go   | CP |     支持     |  HTTP/DNS  |
| Zookeeper| Java | CP |   支持     |   客户端    |

CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性三个需求，因此根据CAP原理将NoSQL分成了满足CA,CP和AP三大类

* CA：单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大
* CP：满足一致性，分区容忍性的系统，通常性能不是很高
* AP：满足可用性，分区容忍性的系统，通常可能对一致性的要求比较低

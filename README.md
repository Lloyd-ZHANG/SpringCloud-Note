# SpringCloud-Note

分布式微服务架构的一站式解决方案，多种微服务架构落地技术的集合体，俗称微服务全家桶

## 微服务架构所需组件类型

### [服务注册中心](https://github.com/AsakiAmane/SpringCloud-Note/tree/main/Notes/Service-Registry)

1. [Eureka](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Registry/Eureka-Note.md) 停更
2. [Zookeeper](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Registry/Zookeeper-Note.md) 可用但用得不多
3. [Consul](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Registry/Consul-Note.md) 保守
4. Nacos

### [服务调用](https://github.com/AsakiAmane/SpringCloud-Note/tree/main/Notes/Service-Calling)

1. [Ribbon](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Calling/Ribbon.md) 半生不熟，也停更
2. [LoadBalancer](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Calling/LoadBalancer.md) 正在尝试取代Ribbon
3. Feign 寄
4. [OpenFeign](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Calling/OpenFeign.md)

### [服务降级、服务熔断、服务限流](https://github.com/AsakiAmane/SpringCloud-Note/tree/main/Notes/Service-Submission)

1. [Hystrix](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Submission/Hystrix.md) 停更
2. Resilience for java 国内较少使用
3. Sentinel ※

### [服务网关](https://github.com/AsakiAmane/SpringCloud-Note/tree/main/Notes/Service-Gateway)

1. Zuul 停更
2. Zuul2 左右手互搏
3. [Gateway](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Gateway/Gateway.md) SpringCloud自研与推荐

### [配置中心](https://github.com/AsakiAmane/SpringCloud-Note/tree/main/Notes/Service-Config)

1. [Config](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Config/Config.md) 正在被后来居上
2. Nacos

### [消息总线](https://github.com/AsakiAmane/SpringCloud-Note/tree/main/Notes/Message-Bus)

1. [Bus](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Message-Bus/Bus.md) 正在被后来居上
2. Nacos

### 消息驱动

[Stream](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Message-Bus/Stream.md)

### 分布式请求链路追踪

Sleuth

### 分布式事务控制

Seata

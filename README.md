# SpringCloud-Note

分布式微服务架构的一站式解决方案，多种微服务架构落地技术的集合体，俗称微服务全家桶

## 微服务架构所需组件类型

### 服务注册中心

1. Euraka 停更
2. Zookeeper 可用
3. Consul 保守
4. Nacos

### 服务调用

1. Ribbon 半生不熟，也停更
2. LoadBalancer 正在尝试取代Ribbon
3. Feign 寄
4. OpenFeign

### 服务降级、服务熔断、服务限流

1. Hystrix 停更
2. Resilience for java 国内较少使用
3. Sentinel ※

### 服务网关

1. Zuul 停更
2. Zuul2 左右手互搏
3. Gateway SpringCloud自研与推荐

### 配置中心

1. Config 正在被后来居上
2. Nacos

### 服务总线

1. Bus 正在被后来居上
2. Nacos

### 消息驱动

Stream

### 分布式请求链路追踪

Sleuth

### 分布式事务控制

Seata

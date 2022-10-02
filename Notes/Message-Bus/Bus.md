# SpringCloud Bus

用来将分布式系统的节点与轻量级消息系统连接起来的框架，整合了Java的事件处理机制和消息中间件的功能。

目前支持RabbitMQ和Kafka。在使用RabbitMQ的情况下，使用SpringCloud Bus会默认为服务创建一个名为springCloudBus的交换机。

## 用以实现动态配置更新的两种思路

1. 利用消息总线触发一个[CLIENT]/bus/refresh，从而刷新所有客户端的配置
2. **（推荐）**利用消息总线触发一个[SERVER]/bus/refresh，从而刷新所有客户端的配置

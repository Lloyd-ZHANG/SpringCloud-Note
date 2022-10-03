# 服务消息总线

在微服务架构的系统中，通常会使用轻量级的消息代理构建一个共用的消息主题，并让系统中所有微服务实例都连接上来。由于该主题中产生的消息会被所有实例监听和消费，因此称其为消息总线。在总线上的各个实例都可以方便地**广播**一些需要让其他连接在该主题上的实例都知道的消息

其中一个应用便可以是：另通过配置中心获取配置信息的client及配置中心都监听总线中的同一个Topic，当配置中心刷新数据时，使配置中心将消息放入Topic中，这样其他监听同一个Topic的服务就能得到通知，从而动态更新自身的配置

## 消息驱动

假设微服务工程之间使用了不同的消息队列MQ，而我们又没有接触过其他工程使用的MQ，这时候便需要一个消息驱动实现交接

## 组件

1. Nacos
2. [Bus](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Message-Bus/Bus.md)
3. [Stream](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Message-Bus/Stream.md) （消息驱动）

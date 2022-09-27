# Zookeeper

一个分布式协调工具，可以用以实现注册中心功能

zookeeper中注册的服务节点是临时节点，当服务宕机而zookeeper接收不到心跳连接之后会清除服务

## 配置

与Eureka不同，Zookeeper服务器并非通过SpringBoot搭建

搭建过程及端口说明可以参考docker-compose.yml

## 版本冲突

详情看cloud-provider-payment8004的pom文件中注释

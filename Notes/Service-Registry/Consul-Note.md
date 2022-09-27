# Consul

Consul是一套开源的分布式服务发现和配置管理系统，用Go语言开发。

Consul提供了微服务系统中的服务治理、配置中心、控制总线等功能，这些功能中的每一个都可以根据需要单独使用，也可以一起使用以构建全方面的服务网络。

总之Consul提供了一种完整的服务网格解决方案

## 优点

1. 基于raft协议，比较简洁
2. 支持健康检查，同时支持http和dns协议
3. 支持跨数据中心的WAN集群，提供图形界面，跨平台

## 基本概念

### server模式和client模式

server模式和client模式是consul节点的类型；**client不是指的用户客户端**。

* server模式提供数据持久化功能。
* client模式不提供持久化功能，并且实际上他也不工作，只是把用户客户端的请求转发到server模式的节点。所以可以把client模式的节点想象成LB(load balance)，只负责请求转发。
* 通常server模式的节点需要配置成多个例如3个，5个。而client模式节点个数没有限制。

### server模式启动的命令行参数

* -server：表示当前使用的server模式；如果没有指定，则表示是client模式。
* -node：指定当前节点在集群中的名称。
* -config-dir：指定配置文件路径，定义服务的；路径下面的所有.json结尾的文件都被访问；缺省值为：/consul/config。
* -data-dir： consul存储数据的目录；缺省值为：/consul/data。
* -datacenter：数据中心名称，缺省值为dc1。
* -ui：使用consul自带的web UI界面 。
* -join：加入到已有的集群中。
* -enable-script-checks： 检查服务是否处于活动状态，类似开启心跳。
* -bind： 绑定服务器的ip地址。
* -client： 客户端可访问ip，缺省值为：“127.0.0.1”，即仅允许环回连接。
* -bootstrap-expect：在一个datacenter中期望的server节点数目，consul启动时会一直等待直到达到这个数目的server才会引导整个集群。这个参数的值在同一个datacenter的所有server节点上必须保持一致。
* -bootstrap，用来控制一个server是否运行在bootstrap模式：当一个server处于bootstrap模式时，它可以选举自己为leader；注意在一个datacenter中只能有一个server处于bootstrap模式。所以这个参数一般只能用在只有一个server的开发环境中，在有多个server的cluster产品环境中，不能使用这个参数，否则如果多个server都标记自己为leader那么会导致数据不一致。另外该标记不能和-bootstrap-expect同时指定。

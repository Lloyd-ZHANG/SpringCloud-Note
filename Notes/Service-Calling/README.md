# 服务调用

## 组件

1. [Ribbon](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Calling/Ribbon.md) 半生不熟，也停更
2. [LoadBalancer](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Calling/LoadBalancer.md) 正在尝试取代Ribbon
3. Feign 寄
4. [OpenFeign](https://github.com/AsakiAmane/SpringCloud-Note/blob/main/Notes/Service-Calling/OpenFeign.md)

目前SpringCloud已默认使用LoadBalancer

## 作用

主要为每个微服务之间的调用实现负载均衡

## RestTemplate

* `GetForObject()` 返回对象为响应体中数据转化成的对象，可以理解为Json
* `GetForEntity()` 返回对象为 `ResponseEntity` 对象，包含了响应中一些重要信息，比如响应头，响应码，响应体等

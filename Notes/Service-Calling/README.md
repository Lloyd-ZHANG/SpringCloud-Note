# 服务调用

## 组件

* Ribbon
* LoadBalancer
* OpenFeign
* Feign (寄了)

目前SpringCloud已默认使用LoadBalancer

## 作用

主要为每个微服务之间的调用实现负载均衡

## RestTemplate

* `GetForObject()` 返回对象为响应体中数据转化成的对象，可以理解为Json
* `GetForEntity()` 返回对象为 `ResponseEntity` 对象，包含了响应中一些重要信息，比如响应头，响应码，响应体等

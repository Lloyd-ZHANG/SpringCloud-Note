# Hystrix

## 简介

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统中，许多依赖不可避免会调用失败，如超时、异常等，Hystrix能保证在一个依赖出现问题的情况下不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性

断路器本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的，可处理的备选响应（FallBack），而不是长时间等待或抛出调用方无法处理的异常，从而保证服务调用方的线程不会被长时间、不必要地占用，最终避免故障在分布式系统中蔓延，乃至雪崩

## 服务降级

客户端去调用服务端，但服务端宕机或已关闭时的处理

### 服务端设置备选响应方法

超时异常和运行异常均可跳至备选响应方法内

1. 在service层的，推断会出现超时的方法上加上注解

```java
@HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {    // 设置备选响应及处理参数
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") // 设置超时峰值
})
```

其中fallbackMethod表示出现异常时的备选响应方法，@HystrixProperty.name表示该处理命令的名称，这里选择的execution.isolation.thread.timeoutInMilliseconds为超时峰值

2. 主启动类激活 `@EnableHystrix`

### 客户端备选响应方法

客户端可以设置等待时间上限，实际上服务降级一般做在客户端

1. 由于客户端搭配Feign使用，因此需要在客户端开启 `feign.circuitbreaker.enable = true`
2. 主启动类激活 `@EnableHystrix`
3. 在客户端的controller层加上同上注解

### 指定默认的备选响应方法

若每个方法都对应一个备选方法，会导致代码膨胀，考虑采用默认备选

1. 编写全局的异常处理方法
2. 在类上加上注解 `@DefaultProperties(defaultFallback = "")`，其中defaultFallback内是方法名
3. 在需要降级的方法上加上注解 `@HystrixCommand`

### 解耦

在之前的情况中，`@HystrixCommand` 注解需要标注在方法上，使得业务逻辑代码与错误处理代码合在一起，耦合度很高，故需要解耦

1. 重新建一个自定义类，实现自定义的 `Feign` 接口
2. 在自定义类中重写接口的方法
3. 开启 `feign.circuitbreaker.enabled = true`
4. 在feign接口的注解 `@FeignClient` 内添加 `fallback = xxx.class`

## 服务熔断

Hystrix的断路器有三种状态：OPEN、HALF_OPEN、CLOSE

### 服务端配置

类比保险丝，达到最大服务访问后直接拒绝访问，拉闸限电。服务熔断后，再有请求调用时将不会调用主逻辑，而是直接调用降级Fallback。通过断路器，实现了自动发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果

```java
@HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 失败到达率
})
```

`circuitBreaker.sleepWindowInMilliseconds` 熔断多少秒后开始尝试恢复（HALF_OPEN），默认5000

`circuitBreaker.requestVolumeThreshold` 滑动窗口大小，即触发熔断的最小请求数量，默认为20；举个例子，一共只有19个请求落在窗口内，全部失败了，也不会触发熔断

`circuitBreaker.errorThresholdPercentage` 失败率打到多少百分比后熔断

## 服务监控

除了隔离依赖服务的调用以外，Hystrix还提供了准实时的调用监控（Hystrix Dashboard），Hystrix会持续记录所有通过Hystrix发起的请求执行信息，并以统计报表和图形的形式展示给用户，包括每秒执行多少请求成功，多少失败等。Netflix通过hystrix-metrics-event-stream项目实现了对以上指标的监控，SpringCloud也提供了Hystrix Dashboard的整合，对监控内容转化成可视化界面

### 步骤

1. 依赖 `spring-cloud-starter-netflix-hystrix-dashboard`
2. 主启动类上添加启动注解 `@EnableHystrixDashboard`
3. 在需要被监控的模块yml配置文件中加入

*！需要注意的是新版本的SpringCloud需要额外在Dashboard模块中引入依赖`spring-cloud-starter-config`*

```yml
management:
    endpoints:
      web:
        exposure:
          include: "*"
```

1. 在监控模块yml配置文件中加入

```yml
hystrix:
    dashboard:
        proxy-stream-allow-list: "*"
```

5. 在监控仪表盘页面（监控模块/hystrix）输入监控地址（监控模块/actuator/hystrix.stream）即可

# SpringCloud Config

SpringCloud Config是微服务架构中的微服务集中化外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的**外部配置**

## 配置

### 配置中心配置

```yml
server:
  port: 3344
spring:
  application:
    name: cloud-config-center
  profiles:
    active: native  # 表示从本地取配置文件
  cloud:
    config:
      server:
        native:
          search-locations: classpath:/config
#        git:
#          uri: git@github.com:AsakiAmane/springcloud-config.git
#          # 搜索目录
#          search-paths:
#            - /
#          force-pull: true
      # 默认分支
      # label: main
```

主启动类加上`@EnableConfigServer`注解

配置中心的返回规则如下：

假设访问URI为：`http://[SERVER]/[name]-[profile].yml`，则会返回以下配置文件：`[name].yml`（代表name这个应用的通用配置）、`[name]-[profile].yml`（代表name应用在profile环境下的配置）、`application.yml`（代表整个应用的通用配置）以及 `application-[profile].yml`（代表整个应用在profile环境下的通用配置。

需要注意的是，若仅声明访问 `http://[SERVER]/[name].yml` 会返回404

### 客户端配置

**加入spring-cloud-starter-bootstrap依赖**

1. 为了防止本地配置被覆盖，需要将配置文件改为 `bootstrap.yml`
2. 在 `bootstrap.yml` 中进行配置：

```yml
spring:
    application:
      name: cloud-config-client
    profiles:
      active: prod
    cloud:
      config:
        name: config # 配置文件名称
        label: master # 分支名称，适用于使用github作为配置文件存放中心的情况
        profile: dev # 读取后缀名称
        uri: http://localhost:3344 # 配置中心地址
```

以上述配置为例，SpringCloud config会为客户端向配置中心访问 `http://localhost:3344/config-dev.yml`，根据服务端返回规则，则会返回 `config.yml`、`config-dev.yml`、`application.yml` 以及 `application-dev.yml`

此外，`cloud.config` 中的 `name` 和 `profile` 会覆盖上面的 `spring.application.name` 和 `spring.application.profiles.active` 属性，这也是为什么 `spring.application.profiles.active` 中选择的环境为prod，取得的却是dev环境的配置文件；若 `cloud.config` 中没有配置 `name` 和 `profile`，则会以 `spring.application.name` 和 `spring.application.profiles.active` 属性的为参照

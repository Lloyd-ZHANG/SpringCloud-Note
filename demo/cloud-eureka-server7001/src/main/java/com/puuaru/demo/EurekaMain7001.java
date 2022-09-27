package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: Eureka注册中心7001
 * @Author: puuaru
 * @Date: 2022/9/27
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer // 声明此为服务中心
public class EurekaMain7001 {
        public static void main(String[] args) {
                SpringApplication.run(EurekaMain7001.class, args);
            }
}

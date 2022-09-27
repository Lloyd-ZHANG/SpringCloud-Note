package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: Payment Service 8004
 * @Author: puuaru
 * @Date: 2022/9/27
 */
@SpringBootApplication
@EnableDiscoveryClient  // 该注解用于向consul或者Zookeeper作为注册中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}

package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @Description: Payment Provider using Hystrix
 * @Author: puuaru
 * @Date: 2022/9/29
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableHystrix
public class PaymentHystrixMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8008.class, args);
    }
}

package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: Provider: Payment 8002
 * @Author: puuaru
 * @Date: 2022/9/27
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8002 {
        public static void main(String[] args) {
                SpringApplication.run(PaymentMain8002.class, args);
            }
}

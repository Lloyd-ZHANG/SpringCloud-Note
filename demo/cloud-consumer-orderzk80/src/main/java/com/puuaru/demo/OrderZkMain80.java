package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: Order Using Zookeeper
 * @Author: puuaru
 * @Date: 2022/9/27
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class OrderZkMain80 {
        public static void main(String[] args) {
                SpringApplication.run(OrderZkMain80.class, args);
            }
}

package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description: Client Application
 * @Author: puuaru
 * @Date: 2022/9/26
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}

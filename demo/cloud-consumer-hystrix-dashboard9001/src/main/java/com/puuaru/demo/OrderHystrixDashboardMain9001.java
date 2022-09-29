package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Description: Hystrix Dashboard Application
 * @Author: puuaru
 * @Date: 2022/9/29
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableHystrixDashboard
public class OrderHystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixDashboardMain9001.class, args);
    }
}

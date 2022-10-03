package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description: Message Provicer Application
 * @Author: puuaru
 * @Date: 2022/10/3
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StreamProviderMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamProviderMain8801.class, args);
    }
}

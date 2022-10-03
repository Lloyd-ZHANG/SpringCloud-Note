package com.puuaru.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description: TODO
 * @Author: puuaru
 * @Date: 2022/10/3
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StreamConsumerMain8803 {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerMain8803.class, args);
    }
}

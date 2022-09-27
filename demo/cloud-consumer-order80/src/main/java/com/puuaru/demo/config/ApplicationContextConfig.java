package com.puuaru.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 注入restTemplate
 * @Author: puuaru
 * @Date: 2022/9/26
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced   // 赋予负载均衡能力
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

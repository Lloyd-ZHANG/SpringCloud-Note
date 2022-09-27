package com.puuaru.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description: Consul Payment Service Controller
 * @Author: puuaru
 * @Date: 2022/9/27
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/paymentConsul")
    public String paymentConsul() {
        return "SpringCloud with consul: " + UUID.randomUUID() + ", Port: " + port;
    }
}

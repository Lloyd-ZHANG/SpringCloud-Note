package com.puuaru.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description: zookeeper Payment Controller
 * @Author: puuaru
 * @Date: 2022/9/27
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/paymentZk")
    public String paymentZk() {
        return "SpringCloud with zookeeper: " + UUID.randomUUID().toString() + ", Port: " + serverPort;
    }
}

package com.puuaru.demo.controller;

import com.puuaru.demo.entity.CommonResult;
import com.puuaru.demo.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: Order Controller
 * @Author: puuaru
 * @Date: 2022/9/26
 */
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    public static final String PAYMENT_URL = "http://localhost:8001";

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("access create");
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("access get");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    }
}

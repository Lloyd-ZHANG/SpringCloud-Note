package com.puuaru.demo.controller;

import com.puuaru.demo.entity.CommonResult;
import com.puuaru.demo.entity.Payment;
import com.puuaru.demo.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Controller using Feign
 * @Author: puuaru
 * @Date: 2022/9/28
 */
@RestController
@Slf4j
public class OrderController {
    @Autowired
    PaymentFeignService paymentFeignService;

    @GetMapping("/payment/consumer/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("Open Feign");
        return paymentFeignService.getPaymentById(id);
    }
}

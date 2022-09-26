package com.puuaru.demo.controller;

import com.puuaru.demo.entity.CommonResult;
import com.puuaru.demo.entity.Payment;
import com.puuaru.demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: PaymentController
 * @Author: puuaru
 * @Date: 2022/9/26
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment")
    public CommonResult<Boolean> create(@RequestBody Payment payment) {
        boolean save = paymentService.save(payment);
        log.info("****** Insert Result: " + save);
        if (save) {
            return new CommonResult<>(200, "Insert Success", save);
        }
        return new CommonResult<>(444, "Insert failed");
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        log.info("Get into getPayment success");
        if (payment != null) {
            return new CommonResult<>(200, "Get data Success, serverPort: " + serverPort, payment);
        }
        return new CommonResult<>(444, "id[" + id + "] has none record");
    }
}

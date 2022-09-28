package com.puuaru.demo.service;

import com.puuaru.demo.entity.CommonResult;
import com.puuaru.demo.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/{id}")    // 与服务端通信
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}

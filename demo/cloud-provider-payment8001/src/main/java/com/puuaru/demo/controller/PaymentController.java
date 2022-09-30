package com.puuaru.demo.controller;

import com.puuaru.demo.entity.CommonResult;
import com.puuaru.demo.entity.Payment;
import com.puuaru.demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment")
    public CommonResult<Boolean> create(@RequestBody Payment payment) {
        boolean save = paymentService.save(payment);
        log.info("****** Insert Result: " + save);
        if (save) {
            return new CommonResult<>(200, "Insert Success", save);
        }
        return new CommonResult<>(444, "Insert failed");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        log.info("Get into getPayment success");
        if (payment != null) {
            return new CommonResult<>(200, "Get data Success, serverPort: " + serverPort, payment);
        }
        return new CommonResult<>(444, "id[" + id + "] has none record");
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(s -> log.info("****** element: " + s));

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(serviceInstance -> log.info(serviceInstance.getInstanceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort() + "\t" + serviceInstance.getUri()));
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}

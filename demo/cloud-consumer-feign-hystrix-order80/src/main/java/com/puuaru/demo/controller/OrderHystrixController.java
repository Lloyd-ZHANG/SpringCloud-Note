package com.puuaru.demo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.puuaru.demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: puuaru
 * @Date: 2022/9/29
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalExceptionHandler")
public class OrderHystrixController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
    @HystrixCommand( // fallbackMethod = "getByIdTimeoutHandler",
            commandProperties = {    // 设置备选响应及处理参数
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500") // 设置超时峰值
    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "Thread Pool: " + Thread.currentThread().getName() + " 80: Target Service error: timeout or exception, id: " + id + "\t :(";
    }

    public String globalExceptionHandler() {
        return "80 Global Exception Handler";
    }
}

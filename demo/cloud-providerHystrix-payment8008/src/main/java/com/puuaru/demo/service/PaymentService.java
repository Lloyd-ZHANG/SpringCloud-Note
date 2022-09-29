package com.puuaru.demo.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Payment Service for simulating timeout
 * @Author: puuaru
 * @Date: 2022/9/29
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "Thread Pool: " + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id + "\t :)";
    }

    // 请求服务的异常处理
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {    // 设置备选响应及处理参数
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") // 设置超时峰值
    })
    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int age = 10 / 0;
        return "Thread Pool: " + Thread.currentThread().getName() + " paymentInfo_Timeout, id: " + id + "\t :(";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "Thread Pool: " + Thread.currentThread().getName() + " 8008 Service error: timeout or exception, id: " + id + "\t :r";
    }


    // ======= 服务熔断 =======
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),   // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),    // 失败到达率
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("****** id cannot be negative");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t Call successfully, serial UUID: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id cannot be negative, current id = " + id;
    }
}

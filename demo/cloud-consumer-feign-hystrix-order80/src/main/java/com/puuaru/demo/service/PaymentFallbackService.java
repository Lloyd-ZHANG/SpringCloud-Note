package com.puuaru.demo.service;

import org.springframework.stereotype.Service;

/**
 * @Description: PaymentService default fallback
 * @Author: puuaru
 * @Date: 2022/9/29
 */
@Service
public class PaymentFallbackService implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "****** PaymentFallbackService 80: paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "****** PaymentFallbackService 80: paymentInfo_timeout";
    }
}

package com.puuaru.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.demo.entity.Payment;
import com.puuaru.demo.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: Payment Service
 * @Author: puuaru
 * @Date: 2022/9/26
 */
public interface PaymentService extends IService<Payment> {
}

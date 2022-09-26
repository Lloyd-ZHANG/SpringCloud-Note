package com.puuaru.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.demo.entity.Payment;
import com.puuaru.demo.mapper.PaymentMapper;
import com.puuaru.demo.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @Description: PaymentService impl using mybatis-plus
 * @Author: puuaru
 * @Date: 2022/9/26
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
}

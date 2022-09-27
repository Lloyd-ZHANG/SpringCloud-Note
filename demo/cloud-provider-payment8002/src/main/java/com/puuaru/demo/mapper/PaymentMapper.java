package com.puuaru.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puuaru.demo.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: Payment Mapper
 * @Author: puuaru
 * @Date: 2022/9/26
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}

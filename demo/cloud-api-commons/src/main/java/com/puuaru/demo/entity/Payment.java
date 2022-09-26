package com.puuaru.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: payment实体类
 * @Author: puuaru
 * @Date: 2022/9/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String serial;
}

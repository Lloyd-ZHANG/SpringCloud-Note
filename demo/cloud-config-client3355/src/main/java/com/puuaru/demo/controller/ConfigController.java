package com.puuaru.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Config Client Controller
 * @Author: puuaru
 * @Date: 2022/9/30
 */
@RestController
@Slf4j
public class ConfigController {
    @Value("${config.info}")
    private String info;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return info;
    }
}

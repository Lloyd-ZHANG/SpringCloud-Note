package com.puuaru.demo.controller;

import com.puuaru.demo.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 消息提供者controller
 * @Author: puuaru
 * @Date: 2022/10/3
 */
@RestController
public class sendMessageController {
    @Autowired
    private MessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}

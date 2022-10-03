package com.puuaru.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.puuaru.demo.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Description: 消息生产者
 * @Author: puuaru
 * @Date: 2022/10/3
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements MessageProvider {
    //@Autowired
    //private StreamBridge streamBridge;

    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String uuid = IdUtil.simpleUUID();
        output.send(MessageBuilder.withPayload(uuid).build());
        log.info("********* send message: " + uuid);
        return null;
    }
}

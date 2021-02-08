package com.lbj.client.kafka.provider.controller;

import com.lbj.client.kafka.provider.service.KafkaSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname KafkaProviderController
 * @Description TODO
 * @Date 2021/2/5 14:56
 * @Created by lbj
 */
@RestController
public class KafkaProviderController {
    @Autowired
    private KafkaSendService kafkaSendService;

    @RequestMapping("/send/{msg}")
    public void send(@PathVariable("msg") String msg) {
        System.out.println("发送消息------->>"+msg);
        kafkaSendService.sendMsg(msg);
    }
}

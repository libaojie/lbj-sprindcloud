package com.lbj.client.kafka.consumer.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

/**
 * @Classname KafkaRecieveService
 * @Description TODO
 * @Date 2021/2/5 16:13
 * @Created by lbj
 */
//消息接收端，stream给我们提供了Sink,Sink源码里面是绑定input的，要跟我们配置文件的input关联的。
@EnableBinding(Sink.class)
public class KafkaRecieveService {
    @StreamListener(Sink.INPUT)
    public void process(Message<?> message) {
        System.out.println("接收到消息----------->>>"+message.getPayload());
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            System.out.println("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
    }
}

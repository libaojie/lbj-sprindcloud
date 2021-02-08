package com.lbj.client.kafka.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Classname KafkaProviderApplication
 * @Description kafka生产端
 * @Date 2021/2/1 13:56
 * @Created by lbj
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KafkaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProviderApplication.class, args);
        log.info("---------------客户端 系统启动成功------------------");
    }
}

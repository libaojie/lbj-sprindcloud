package com.lbj.client.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Classname DemoApplication
 * @Description TODO
 * @Date 2021/2/1 13:56
 * @Created by lbj
 */
@Slf4j
@EnableAsync // 异步线程调用
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        log.info("---------------客户端 系统启动成功------------------");
    }
}

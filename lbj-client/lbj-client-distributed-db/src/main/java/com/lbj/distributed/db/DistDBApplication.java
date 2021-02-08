package com.lbj.distributed.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Classname DistDBApplication
 * @Description 基于数据库的分布式锁
 * @Date 2021/2/8 15:21
 * @Created by lbj
 */
@Slf4j
@ServletComponentScan
@SpringBootApplication
public class DistDBApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistDBApplication.class, args);
        log.info("---------------基于数据库的分布式锁 客户端 系统启动成功------------------");
    }
}

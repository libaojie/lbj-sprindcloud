package com.lbj.distributed.db;

import com.lbj.common.client.config.MyWebMvcConfigurerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @Classname DistDBApplication
 * @Description 基于数据库的分布式锁
 * @Date 2021/2/8 15:21
 * @Created by lbj
 */
@Slf4j
@ServletComponentScan
@SpringBootApplication
@ComponentScan({"com.lbj.common.client.component", "com.lbj.distributed.db"})
@Import({MyWebMvcConfigurerAdapter.class})
public class DistDBApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistDBApplication.class, args);
        log.info("---------------基于数据库的分布式锁 客户端 系统启动成功------------------");
    }
}

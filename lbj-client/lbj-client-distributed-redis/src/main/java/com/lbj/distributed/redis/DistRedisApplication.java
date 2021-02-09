package com.lbj.distributed.redis;

import com.lbj.common.client.config.MyWebMvcConfigurerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.lbj.common.client", "com.lbj.distributed.redis"})
@Import({MyWebMvcConfigurerAdapter.class})
public class DistRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistRedisApplication.class, args);
        log.info("---------------基于redis的分布式锁 客户端 系统启动成功------------------");
    }
}

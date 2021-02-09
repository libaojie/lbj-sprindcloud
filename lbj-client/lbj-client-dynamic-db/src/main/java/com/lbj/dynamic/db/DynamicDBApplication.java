package com.lbj.dynamic.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Classname DynamicDBApplication
 * @Description 动态数据源切换
 * @Date 2021/2/9 9:18
 * @Created by lbj
 */
@Slf4j
@ServletComponentScan
@SpringBootApplication
@ComponentScan({"com.lbj.common.client", "com.lbj.dynamic.db"})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration(exclude={DruidDataSourceAutoConfigure.class})
//@Import({MyWebMvcConfigurerAdapter.class})
public class DynamicDBApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicDBApplication.class, args);
        log.info("---------------动态切库 客户端 系统启动成功------------------");
    }
}

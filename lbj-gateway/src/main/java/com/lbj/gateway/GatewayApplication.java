package com.lbj.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
// TODO
//@EnableFeignClients(basePackages = {"com.hirisun.feign.auth"})
public class GatewayApplication {

    private static final Logger log = LoggerFactory
            .getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        log.info(">>>>>>>>>>>>>>> dmat-gateway 服务网关 组件 启动完成<<<<<<<<<<<<<");
    }
}

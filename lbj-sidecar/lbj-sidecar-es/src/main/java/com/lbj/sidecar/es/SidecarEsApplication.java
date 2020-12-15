package com.lbj.sidecar.es;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@EnableSidecar
@SpringBootApplication
public class SidecarEsApplication {

    private static final Logger logger = LoggerFactory.getLogger(SidecarEsApplication.class);

    public static void main(String[] args) {

        logger.info(">>>>>>>>>>>>>>> lbj-sidecar-es 搜索引擎 桥连 开始启动<<<<<<<<<<<<<");
        SpringApplication.run(SidecarEsApplication.class, args);
        logger.info(">>>>>>>>>>>>>>> lbj-sidecar-es 搜索引擎 桥连 启动完成<<<<<<<<<<<<<");

    }
}

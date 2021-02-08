package com.lbj.common.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname CorsConfiguration
 * @Description 允许跨域访问
 * @Date 2021/2/8 17:30
 * @Created by lbj
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        // 允许跨域访问资源定义： /api/ 所有资源
        corsRegistry.addMapping("*")
                .allowedOrigins("*")
                // 只允许本地的9000端口访问
//                .allowedOrigins("http://localhost:9000", "http://127.0.0.1:9000")
                // 允许发送Cookie
                .allowCredentials(true)
                // 允许所有方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .maxAge(3600);
    }
}

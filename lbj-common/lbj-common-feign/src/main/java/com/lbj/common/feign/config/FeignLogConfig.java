package com.lbj.common.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @class_name: FeignLogConfig
 * @description: 配置feign日志输出
 **/

/**
 * Feign logging
 * A logger is created for each Feign client created. By default the name of the logger is the full class name of the interface used to create the Feign client. Feign logging only responds to the DEBUG level.
 *
 *   application.yml
 *
 * logging.level.project.user.UserClient: DEBUG
 *
 *
 * The Logger.Level object that you may configure per client, tells Feign how much to log. Choices are:
 *
 * 可以为每个客户端配置日志级别(Logger.Level)，选项有：
 *
 * NONE, No logging (DEFAULT).
 * BASIC, Log only the request method and URL and the response status code and execution time.
 * HEADERS, Log the basic information along with request and response headers.
 * FULL, Log the headers, body, and metadata for both requests and responses.
 * For example, the following would set the Logger.Level to FULL:
 *
 * 下例将日志级别设为全部(FULL)：
 *
 * @Configuration
 * public class FooConfiguration {
 *     @Bean
 *     Logger.Level feignLoggerLevel() {
 *         return Logger.Level.FULL;
 *     }
 * }
 */

@Configuration
public class FeignLogConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

package com.lbj.distributed.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname RedissonConfig
 * @Description TODO
 * @Date 2021/2/9 17:02
 * @Created by lbj
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
//        config.useClusterServers()
//                .setScanInterval(2000)
//                .addNodeAddress("redis://192.168.160.165:16379")
//                .addNodeAddress("redis://192.168.160.165:16379");
        config.useSingleServer()
                .setAddress("redis://192.168.160.165:16379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}

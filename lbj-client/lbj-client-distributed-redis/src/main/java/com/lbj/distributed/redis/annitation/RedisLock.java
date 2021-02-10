package com.lbj.distributed.redis.annitation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname RedisLock
 * @Description 基于Redis的分布式锁
 * @Date 2021/2/10 11:00
 * @Created by lbj
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    String key() default "test";
}

package com.lbj.distributed.redis.aspect;

import com.lbj.common.client.ClientConstant;
import com.lbj.common.client.base.BaseContextHandler;
import com.lbj.distributed.redis.annitation.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Classname RedisLockAspect
 * @Description TODO
 * @Date 2021/2/10 11:07
 * @Created by lbj
 */
@Slf4j
@Aspect
@Component
public class RedisLockAspect {

    @Autowired
    RedissonClient redissonClient;

    //声明切面类路径，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
    @Pointcut("execution(* com.lbj.*.*.*(..))")
    public void point() {
    }


    @Around("@annotation(redisLock)")
    public Object around(ProceedingJoinPoint pjp, RedisLock redisLock) {
        Object obj = null;

        RLock lock = redissonClient.getLock(redisLock.key());

        try {
            log.info("【{}】Try", BaseContextHandler.get(ClientConstant.RequestUUID));
            //尝试加锁，最多等待6秒，上锁以后10秒自动解锁
            if (lock.tryLock(6, 10, TimeUnit.SECONDS)) {
                try {
                    obj = pjp.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                } finally {
                    log.info("【{}】Unlock", BaseContextHandler.get(ClientConstant.RequestUUID));
                    if (lock.isLocked()) {
                        lock.unlock();
                    }
                }
            } else {
                log.info("【{}】False", BaseContextHandler.get(ClientConstant.RequestUUID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("【{}】finally", BaseContextHandler.get(ClientConstant.RequestUUID));
        }

        return obj;
    }
}

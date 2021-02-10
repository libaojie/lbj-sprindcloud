package com.lbj.distributed.redis.controller;

import com.lbj.common.client.ClientConstant;
import com.lbj.common.client.base.BaseContextHandler;
import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.ret.CommRes;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2021/2/8 16:28
 * @Created by lbj
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    RedissonClient redissonClient;

    /**
     * 乐观锁
     * @return
     * @throws Exception
     */
    @RequestMapping("/test/redisson/")
    public CommRes test1() throws Exception {

        String lockKey = "test";
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean res = lock.tryLock(0,10, TimeUnit.SECONDS);
            if (res) {
                return new CommRes(ErrorCode.SUCCESS, "锁定");
            }
            else{
                return new CommRes(ErrorCode.SUCCESS, "操作太频繁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return new CommRes(ErrorCode.SUCCESS, "完成");

    }

    /**
     * 悲观锁
     * @return
     * @throws Exception
     */
    @RequestMapping("/test/redisson/pessimistic")
    public CommRes test2() throws Exception {

        String lockKey = "test";
        RLock lock = redissonClient.getLock(lockKey);

        try{
            log.info("【{}】Try", BaseContextHandler.get(ClientConstant.RequestUUID));
            //尝试加锁，最多等待6秒，上锁以后10秒自动解锁
            if (lock.tryLock(6, 10, TimeUnit.SECONDS)){
                try{
                    log.info("【{}】True", BaseContextHandler.get(ClientConstant.RequestUUID));
                    Thread.sleep(10000);
                    return new CommRes(ErrorCode.SUCCESS, "获取锁");
                } catch (Exception e){

                }finally {
                    log.info("【{}】Unlock", BaseContextHandler.get(ClientConstant.RequestUUID));
                    if (lock.isLocked()){
                        lock.unlock();
                    }
                }
            }
            else{
                log.info("【{}】False", BaseContextHandler.get(ClientConstant.RequestUUID));
                return new CommRes(ErrorCode.SUCCESS, "未获取锁");
            }
        } catch (Exception e){
            e.printStackTrace();
            return new CommRes(ErrorCode.SUCCESS, "失败");
        }finally {
            log.info("【{}】finally", BaseContextHandler.get(ClientConstant.RequestUUID));
        }
        return new CommRes(ErrorCode.SUCCESS, "结束");
    }



}

package com.lbj.client.demo.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Description 异步
 * @Date 2021/3/10 14:38
 * @File AsyncService
 * @Version v1.0
 * @Date 2021/2/5 14:56
 * @Created by libaojie
 */
@Slf4j
@Component
public class AsyncService {

    /**
     * 不含返回值的异步函数
     */
    @Async
    public void async(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("当前子线程：{}", Thread.currentThread().getName());
    }

    /**
     * 含返回值的异步函数
     * @return
     */
    @Async
    public Future<String> asyncTask(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<String>("asyncTask");
    }
}

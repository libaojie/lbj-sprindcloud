package com.lbj.client.demo.home;




import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.ret.CommRes;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "")
@Api(value = "应用指标的api", tags = {"应用指标的api"})
@Slf4j
public class HomeController {

    @Autowired
    AsyncService asyncService;

    @RequestMapping("/hi")
    public CommRes<HomeHi> hi(HttpServletRequest request) {
        HomeHi homeHi = new HomeHi();
        homeHi.setPort("100");
        return new CommRes(ErrorCode.SUCCESS, homeHi);
    }


    /**
     * 异步非阻塞线程  curl 127.0.0.1:16001/async
     * @param request
     * @return
     */
    @RequestMapping("/async")
    public CommRes<HomeHi> async(HttpServletRequest request) {
        HomeHi homeHi = new HomeHi();
        homeHi.setPort("100");
        log.info("主线程名称；{}", Thread.currentThread().getName());
        asyncService.async();
        return new CommRes(ErrorCode.SUCCESS, homeHi);
    }

    /**
     * 异步阻塞线程 curl 127.0.0.1:16001/asyncTask
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/asyncTask")
    public CommRes<HomeHi> asyncTask(HttpServletRequest request)  throws Exception {
        HomeHi homeHi = new HomeHi();
        homeHi.setPort("100");
        log.info("主线程名称；{}", Thread.currentThread().getName());
        Future<String> future = asyncService.asyncTask();
        while(true){
            if (future.isCancelled()){
                log.info("子线程停止");
                break;
            }
            if (future.isDone()){
                try {
                    log.info("子线程执行完毕，结果是：{}", future.get());
                    homeHi.setPort(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return new CommRes(ErrorCode.SUCCESS, homeHi);
    }



}


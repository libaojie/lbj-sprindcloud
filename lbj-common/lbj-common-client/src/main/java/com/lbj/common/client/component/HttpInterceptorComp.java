package com.lbj.common.client.component;

import com.lbj.common.client.ClientConstant;
import com.lbj.common.client.base.BaseContextHandler;
import com.lbj.common.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname HttpInterceptorComp
 * @Description http请求拦截
 * @Date 2021/2/8 17:38
 * @Created by lbj
 */
@Slf4j
@Component
public class HttpInterceptorComp implements HandlerInterceptor {

    /**
     * preHandle 方法会在请求处理前被调用。这个方法返回boolean值，如果返回true则继续往下执行，如果返回false则中断。
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        log.info("-------------------------请求开始-------------------------");
        String uuid = UuidUtil.getUUID32();
        long startTime = System.currentTimeMillis();
        log.info("编号【{}】,路径【{}】,开始【{}】", uuid, request.getRequestURL(), startTime);

        // 设置开始请求时间
        BaseContextHandler.set(ClientConstant.RequestStartTime, startTime);
        BaseContextHandler.set(ClientConstant.RequestUUID, uuid);
        return true;
    }


    /**
     * postHandle 方法会在请求处理后，继续调用。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        String uuid = BaseContextHandler.getStr(ClientConstant.RequestUUID);
        long startTime = (long) BaseContextHandler.get(ClientConstant.RequestStartTime);
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        log.info("编号【{}】,路径【{}】,开始【{}】,结束【{}】,耗时【{}】毫秒", uuid, request.getRequestURL(), startTime, endTime, costTime);
        log.info("######################请求结束######################");
    }

    /**
     * afterCompletion 方法会在视图渲染之后调用。
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws IOException
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws IOException {
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        //TODO 从这里做异常捕获及处理没意义
        String flag = ex == null ? "no" : "yes";
        log.info("是否检查到异常：" + flag);
        if (ex != null) {
            log.error("错误信息为：" + ex);
        } else {
            log.info("请求正常结束");
        }
        BaseContextHandler.remove();

    }
}

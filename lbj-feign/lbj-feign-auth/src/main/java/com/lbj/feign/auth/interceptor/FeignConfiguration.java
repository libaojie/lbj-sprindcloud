package com.lbj.feign.auth.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 转发所有的header
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // ----------------------------------------直接塞参数 ----------------------------------------
//        template.header("ApplicationCode", "dmatdmp");
//        template.header("AuthUserName", "admin_all");
//        template.header("AuthPassword", "123456");
        // ------------------------------------------------------------------------------------------
//        HttpServletRequest request = attributes.getRequest();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                String values = request.getHeader(name);
//                template.header(name, values);
//
//            }
//            logger.info("feign interceptor header:{}", template);
//        }
//
//        // 转发参数
//        Enumeration<String> bodyNames = request.getParameterNames();
//        StringBuilder body = new StringBuilder();
//        if (bodyNames != null) {
//            while (bodyNames.hasMoreElements()) {
//                String name = bodyNames.nextElement();
//                String values = request.getParameter(name);
//                body.append(name).append("=").append(values).append("&");
//            }
//        }
//        if (body.length() != 0) {
//            body.deleteCharAt(body.length() - 1);
//            template.body(body.toString());
//            //logger.info("feign interceptor body:{}",body.toString());
//        }
        logger.info("AuthFeignCilent过滤器");
    }

}
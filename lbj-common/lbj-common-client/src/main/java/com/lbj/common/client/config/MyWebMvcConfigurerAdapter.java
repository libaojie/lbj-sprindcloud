package com.lbj.common.client.config;

import com.lbj.common.client.component.HttpInterceptorComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Classname MyWebMvcConfigurerAdapter
 * @Description 请求拦截
 * @Date 2021/2/8 17:35
 * @Created by lbj
 */
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    private HttpInterceptorComp myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/druid/**"); //swagger
        super.addInterceptors(registry);
    }
}

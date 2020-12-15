package com.lbj.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.constants.Attribute;
import com.lbj.common.core.constants.Header;
import com.lbj.common.core.ret.CommRes;
import com.lbj.feign.auth.client.AuthFeignClient;
import com.lbj.feign.auth.entity.RepCommon;
import com.lbj.gateway.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * APP是否登陆
 * 拦截过滤器
 *
 * @author siguiyang
 */
@Component
public class LoginFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);


    @Value("${lbj-auth.enable}")
    boolean authEnable;

    @Autowired
    AuthFeignClient authFeignClient;

    /**
     * Process the Web request and (optionally) delegate to the next
     * {@code WebFilter} through the given {@link GatewayFilterChain}.
     *
     * @param serverWebExchange the current server exchange
     * @param chain             provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain chain) {

        logger.info("*****网关拦截开始*****");
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        serverWebExchange.getAttributes().put(Attribute.Gateway_Time_Begin, System.currentTimeMillis());


        logger.info("*****解析参数*****");

        // 请求方式
        String mode = request.getMethod().toString();
        // 获取请求地址
        String url = request.getURI().getPath();
        // 客户端的系统版本
        String agent = request.getHeaders().get(Header.UserAgent).toString();
        // 获取客户端ip
        String remoteIp = IpUtil.getIpAddr(request);    //取得客户端的IP

        if (!authEnable) {
            // 无需认证
            logger.info("无需认证，直接转发！");
            return chain.filter(serverWebExchange).then(
                    Mono.fromRunnable(() -> this.end(serverWebExchange))
            );
        }

        logger.info("***开始认证***");

        // 需要鉴权认证
        logger.info("获取鉴权参数");
        String application = this.getHeaderParme(request, Header.Application);
        String applicationCode = this.getHeaderParme(request, Header.ApplicationCode);
        String authorization = this.getHeaderParme(request, Header.Authorization);
        String authUserName = this.getHeaderParme(request, Header.AuthUserName);
        String authPassword = this.getHeaderParme(request, Header.AuthPassword);

        CommRes<RepCommon> ret = null;
        try {
            logger.info("开始鉴权");
            ret = authFeignClient.auth(application, applicationCode, authorization, authUserName, authPassword, remoteIp, url, mode, agent);
        } catch (Exception e) {
            String error = "AuthFeignClient结果返回错误：{" + e.getMessage() + "}";
            logger.error(error);
            return this.getVoidMono(serverWebExchange, error);
        }

        if (!ret.getCode().equals(ErrorCode.SUCCESS.getCode())) {
            // 鉴权失败
            logger.warn("鉴权失败：{}", ret.getCode());
            return this.getVoidMono(serverWebExchange, ret);
        }
        logger.info("鉴权成功");

        String userId = "";
        String userName = "";
        String loginName = "";
        if (ret.getData() != null) {
            userId = ret.getData().getUserId();
            userName = URLEncoder.encode(ret.getData().getUserName());
            loginName = ret.getData().getLoginName();
        }

        // 附加Attributes
        mutate.header(Header.userId, userId)
              .header(Header.userName, userName)
              .header(Header.loginName, loginName);
        ServerHttpRequest build = mutate.build();
        return chain.filter(serverWebExchange.mutate().request(build).build()).then(
                Mono.fromRunnable(() -> this.end(serverWebExchange))
        );


    }


    @Override
    public int getOrder() {
        return -1000;
    }

    /**
     * 获取header里面的参数
     *
     * @param request
     * @param key
     * @return
     */
    private String getHeaderParme(ServerHttpRequest request, String key) {
        if (request != null) {
            HttpHeaders httpHeaders = request.getHeaders();
            if (httpHeaders != null) {
                Object value = httpHeaders.get(key);
                if (value != null) {
                    List list = ((List) value);
                    if (list.size() > 0 && list.get(0) != null) {
                        return list.get(0).toString();
                    }

                }
            }
        }
        return "";

    }

    /**
     * 网关抛异常
     *
     * @param body
     */
    @NotNull
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, Object body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer)).then(
                Mono.fromRunnable(() -> this.end(serverWebExchange))
        );
    }

    /**
     * 请求结束回调
     *
     * @param serverWebExchange
     */
    private void end(ServerWebExchange serverWebExchange) {
        Long startTime = serverWebExchange.getAttribute(Attribute.Gateway_Time_Begin);
        if (startTime != null) {
            logger.info(serverWebExchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
        }
        logger.info("-------------------------网关拦截结束-------------------------");
    }


}


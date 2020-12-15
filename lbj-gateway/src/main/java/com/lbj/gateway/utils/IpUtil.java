package com.lbj.gateway.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;


/**
 * 针对Http
 */
public class IpUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(IpUtil.class);

    public static String getIpAddr(ServerHttpRequest request) {

        HttpHeaders httpHeaders = request.getHeaders();
        String ip = IpUtil.getIpString(httpHeaders, "x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = IpUtil.getIpString(httpHeaders, "Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = IpUtil.getIpString(httpHeaders, "WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = IpUtil.getIpString(httpHeaders, "HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = IpUtil.getIpString(httpHeaders, "HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = IpUtil.getIpString(httpHeaders, "X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().toString();
        }

        if ("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }
        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ip != null && ip.length() != 0) {
            ip = ip.split(",")[0];
        }

        return ip;

    }

    private static String getIpString(HttpHeaders httpHeaders, String key){
        Object ret = httpHeaders.get(key);
        if (ret == null){
            return null;
        }
        else{
            return ret.toString();
        }
    }
}


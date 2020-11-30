package com.lbj.common.util.network;

import com.lbj.common.util.type.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 针对Servlet
 */
@Slf4j
public class IpUtil {


    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
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


    /**
     * 对URL地址进行解析
     * @param url
     * @return
     */
    public static String[] getIpAndPort(String url) {
//        Map<String, String> ipAndPort = new HashMap<>();
        String ipPort = "";
        //定义ip:port表达式
        String reg = "(((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5]).){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\:(\\d+))";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(url);
        while (m.find()) {
            ipPort = m.group(0);
        }
        if (StringUtil.isEmpty(ipPort)) {
            return null;
        }
        String[] array = ipPort.split(":");
        if (array.length <= 1) {
            return null;
        }
        return array;
    }

}

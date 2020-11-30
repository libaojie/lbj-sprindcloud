package com.lbj.common.util.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UrlUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(UrlUtil.class);

    public static String handle(String url) {

        // 处理url
        if (url.length() > 0 && url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;

    }


}


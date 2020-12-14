package com.lbj.common.client.util;

import com.lbj.common.client.wrapper.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class ResponseUtil {

    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);
    public static HttpServletResponse handleResponse(HttpServletResponse response, String ciphertext) throws Exception {
        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        byte[] content = responseWrapper.getContent();//获取返回值
        //判断是否有值
        if (content.length < 1) {

            String str = new String(content, StandardCharsets.UTF_8);
            log.info("返回值:" + str);

            //把返回值输出到客户端
            ServletOutputStream out = response.getOutputStream();
            out.write(ciphertext.getBytes());
            out.flush();
            out.close();
        }
        return response;
    }
}

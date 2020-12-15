package com.lbj.feign.auth.client;


import com.lbj.common.core.constants.Header;
import com.lbj.common.core.ret.CommRes;
import com.lbj.feign.auth.entity.RepCommon;
import com.lbj.feign.auth.interceptor.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(value = "lbj-client-ca", configuration = FeignConfiguration.class)
public interface AuthFeignClient {

    /**
     * 权限认证
     *
     * @return
     */
    @RequestMapping(value = "/api/v1/ca/auth", method = RequestMethod.GET)
    CommRes<RepCommon> auth(
            @RequestHeader(Header.Application) String application,
            @RequestHeader(Header.ApplicationCode) String applicationCode,
            @RequestHeader(Header.Authorization) String authorization,
            @RequestHeader(Header.AuthUserName) String authUserName,
            @RequestHeader(Header.AuthPassword) String AuthPassword,
            @RequestParam("ip") String ip,
            @RequestParam("url") String url,
            @RequestParam("type") String type,
            @RequestParam("agent") String agent
    );

}

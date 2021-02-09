package com.lbj.distributed.redis.home;




import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.ret.CommRes;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "")
@Api(value = "应用指标的api", tags = {"应用指标的api"})
@Slf4j
public class HomeController {


    @RequestMapping("/hi")
    public CommRes<HomeHi> hi(HttpServletRequest request) {
        HomeHi homeHi = new HomeHi();
        homeHi.setPort("100");
        return new CommRes(ErrorCode.SUCCESS, homeHi);
    }

}


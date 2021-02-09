package com.lbj.distributed.redis.controller;

import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.ret.CommRes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2021/2/8 16:28
 * @Created by lbj
 */
@RestController
public class TestController {

    @RequestMapping("test1")
    public CommRes test1() throws Exception {

        return new CommRes(ErrorCode.SUCCESS, "完成");
    }


}

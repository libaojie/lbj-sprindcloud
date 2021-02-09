package com.lbj.distributed.redis.home;

import com.lbj.common.client.annotation.RunTime;
import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.ret.CommRes;
import com.lbj.distributed.redis.compent.RedisComp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test/redis")
@Slf4j
public class TestRedisController {

    @Autowired
    private RedisComp redisComp;

    @RunTime
    @GetMapping("/get")
    public CommRes get(@RequestParam(defaultValue = "key") String key) {
        String value = (String) redisComp.get(key);
        return new CommRes(ErrorCode.SUCCESS, value);
    }

    @GetMapping("/set")
    public CommRes set(@RequestParam(defaultValue = "key") String key,
                       @RequestParam(defaultValue = "value") String value) {
        boolean ret = redisComp.set(key, value, 86400);
        return new CommRes(ErrorCode.SUCCESS, ret);
    }

}

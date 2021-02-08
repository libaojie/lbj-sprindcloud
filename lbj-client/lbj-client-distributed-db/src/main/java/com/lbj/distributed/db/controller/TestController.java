package com.lbj.distributed.db.controller;

import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.ret.CommRes;
import com.lbj.distributed.db.dao.DistributeLockMapper;
import com.lbj.distributed.db.model.DistributeLock;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2021/2/8 16:28
 * @Created by lbj
 */
@RestController
public class TestController {
    @Resource
    private DistributeLockMapper distributeLockMapper;

    @RequestMapping("singleLock")
    @Transactional(rollbackFor = Exception.class)
    public CommRes singleLock() throws Exception {
        DistributeLock distributeLock = distributeLockMapper.selectDistributeLock("test");
        if (distributeLock == null) {
            return new CommRes(ErrorCode.SUCCESS, "找不到锁");
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommRes(ErrorCode.SUCCESS, "完成");
    }

    @RequestMapping("list")
    public CommRes list() throws Exception {
        List<DistributeLock> distributeLock = distributeLockMapper.find();
        return new CommRes(ErrorCode.SUCCESS, distributeLock);
    }

}

package com.lbj.dynamic.db.controller;

import com.lbj.common.client.annotation.RunTime;
import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.ret.CommRes;
import com.lbj.dynamic.db.annitation.TargetDataSource;
import com.lbj.dynamic.db.enumModel.DataSourceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2021/2/8 16:28
 * @Created by lbj
 */
@RestController
public class TestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("test1")
    public CommRes test1() throws Exception {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_dist_db");

        // 执行
        return new CommRes(ErrorCode.SUCCESS, maps);
    }


    @TargetDataSource(dataSourceKey = DataSourceKey.MYSQL2)
    @RequestMapping("test2")
    public CommRes test2() throws Exception {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_dist_db");

        // 执行
        return new CommRes(ErrorCode.SUCCESS, maps);
    }
}

package com.lbj.dynamic.db.aspect;

import com.lbj.dynamic.db.enumModel.DataSourceKey;
import com.lbj.dynamic.db.holder.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Classname DynamicRoutingDataSource
 * @Description 获取当前数据源
 * @Date 2021/2/9 10:20
 * @Created by lbj
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
        DataSourceKey dataSourceKey = DynamicDataSourceContextHolder.get();
        log.info("当前数据源：【{}】", dataSourceKey);
        return dataSourceKey;
    }
}

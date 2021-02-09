package com.lbj.dynamic.db.annitation;

import com.lbj.dynamic.db.enumModel.DataSourceKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname TargetDataSource
 * @Description 数据源类型
 * @Date 2021/2/9 9:50
 * @Created by lbj
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
    DataSourceKey dataSourceKey() default DataSourceKey.MYSQL1;
}

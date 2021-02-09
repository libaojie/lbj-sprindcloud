package com.lbj.dynamic.db.aspect;

import com.lbj.dynamic.db.annitation.TargetDataSource;
import com.lbj.dynamic.db.enumModel.DataSourceKey;
import com.lbj.dynamic.db.holder.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Classname DynamicDataSourceAspect
 * @Description 动态配置数据源
 * @Date 2021/2/9 9:52
 * @Created by lbj
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

    //声明切面类路径，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
    @Pointcut("execution(* com.lbj.*.*.*(..))")
    public void point() {
    }

    /**
     * 执行方法前更换数据源
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @Before("@annotation(targetDataSource)") // 判断是否存在targetDataSource注解
    public void doBefore(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        // 获取注解的值
        DataSourceKey dataSourceKey = targetDataSource.dataSourceKey();
        // 设置当前线程的数据源
        if (dataSourceKey == DataSourceKey.MYSQL1) {
            log.info(String.format("设置数据源为  %s", DataSourceKey.MYSQL1));
            DynamicDataSourceContextHolder.set(DataSourceKey.MYSQL1);
        } else if (dataSourceKey == DataSourceKey.MYSQL2) {
            log.info(String.format("设置数据源为  %s", DataSourceKey.MYSQL2));
            DynamicDataSourceContextHolder.set(DataSourceKey.MYSQL2);
        }
    }

    /**
     * 执行方法后清除数据源设置
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @After("@annotation(targetDataSource)")
    public void doAfter(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        log.info(String.format("当前数据源  %s  执行清理方法", targetDataSource.dataSourceKey()));
        DynamicDataSourceContextHolder.clear();
    }

}

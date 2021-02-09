package com.lbj.dynamic.db.holder;

import com.lbj.dynamic.db.enumModel.DataSourceKey;

/**
 * @Classname DynamicDataSourceContextHolder
 * @Description TODO
 * @Date 2021/2/9 10:05
 * @Created by lbj
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<DataSourceKey> currentDataSource = new ThreadLocal<DataSourceKey>();

    /**
     * 清理当前数据源
     */
    public static void clear() {
        currentDataSource.remove();
    }

    /**
     * 获取当前数据源
     *
     * @return
     */
    public static DataSourceKey get() {
        return currentDataSource.get();
    }

    /**
     * 设置数据源
     *
     * @param value
     */
    public static void set(DataSourceKey value) {
        currentDataSource.set(value);
    }

    /**
     * 设置默认数据源
     */
    public static void setSlave() {
        DynamicDataSourceContextHolder.set(DataSourceKey.MYSQL1);
    }

}

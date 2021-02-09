package com.lbj.dynamic.db.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.lbj.dynamic.db.aspect.DynamicRoutingDataSource;
import com.lbj.dynamic.db.enumModel.DataSourceKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DynamicDataSourceConfig
 * @Description 动态数据源配置
 * @Date 2021/2/9 10:22
 * @Created by lbj
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "multiple.mysql1")
    public DataSource dbMysql1(){return DruidDataSourceBuilder.create().build();}

    @Bean
    @ConfigurationProperties(prefix = "multiple.mysql2")
    public DataSource dbMysql2(){return DruidDataSourceBuilder.create().build();}

    @Bean
    public DataSource dynamicDataSource(){
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setDefaultTargetDataSource(dbMysql1());
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>(2);
        dataSourceMap.put(DataSourceKey.MYSQL1, dbMysql1());
        dataSourceMap.put(DataSourceKey.MYSQL2, dbMysql2());
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dynamicDataSource());
        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate sqlSessionFactoryBean() throws Exception{
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dynamicDataSource());
        return namedParameterJdbcTemplate;
    }
}

package com.lbj.distributed.db.dao;


import com.lbj.distributed.db.model.DistributeLock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname DistributeLockMapper
 * @Description TODO
 * @Date 2021/2/8 16:18
 * @Created by lbj
 */
@Mapper
public interface DistributeLockMapper {
    DistributeLock selectDistributeLock(@Param("code") String code);
}

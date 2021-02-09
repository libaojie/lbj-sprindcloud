package com.lbj.distributed.redis.model;

import lombok.Data;

/**
 * @Classname DistributeLock
 * @Description TODO
 * @Date 2021/2/8 16:16
 * @Created by lbj
 */
@Data
public class DistributeLock {

    private Integer id;
    private String code;
    private String name;


}

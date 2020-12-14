package com.lbj.common.client.base;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @param <T>
 * @author li
 */
@Mapper
public interface BaseDao<T> {

    int insert(T model);

    int update(T model);

    boolean remove(T model);

    T getbyId(String id);

    //T get(T t);

    List<T> find(T model);

}
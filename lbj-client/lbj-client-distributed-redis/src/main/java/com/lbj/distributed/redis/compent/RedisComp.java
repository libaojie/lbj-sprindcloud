package com.lbj.distributed.redis.compent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisComp {

    // 一天秒数
    private static final long HOUR24 = 86400;
    // 一小时秒数
    private static final long HOUR1 = 3600;


    @Autowired
    private RedisTemplate redisTemplate;

    //-------------------------------------------------------------------------------
    //  华丽丽的分割线
    //*******************************************************************************
    //  简单操作操作
    //-------------------------------------------------------------------------------

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            
            log.error("redis操作错误",e);
            return false;
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    private boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis操作错误",e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time,
                        TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("redis操作错误",e);
            return false;
        }
    }

    /**
     * 普通缓存获取（自定义时间【秒】）
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key, long time, CallBack callBack) {
        if (key == null) {
            return null;
        }
        if (this.hasKey(key)) {
            return this.get(key);
        }
        Object object = callBack.execute();
        if (object != null) {
            this.set(key, object, time);
        }
        return object;
    }

    /**
     * 普通缓存获取（24小时）
     *
     * @param key 键
     * @return 值
     */
    public Object get24H(String key, CallBack callBack) {
        return this.get(key, HOUR24, callBack);
    }

    public Object get1H(String key, CallBack callBack) {
        return this.get(key, HOUR1, callBack);
    }

    /**
     * 普通缓存获取(永久)
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key, CallBack callBack) {
        if (key == null) {
            return null;
        }
        if (this.hasKey(key)) {
            return this.get(key);
        }
        Object object = callBack.execute();
        if (object != null) {
            this.set(key, object);
        }
        return object;
    }

    /**
     * 获取指定hash 类型 key 中的 field 对应的值
     *
     * @param key
     * @param field
     * @return
     */
    public Object getMap(String key, String field) {
        if (key == null) {
            return null;
        }
        if (this.hasKey(key)) {
            return redisTemplate.opsForHash().get(key, field);
        }
        return null;
    }

    /**
     * hash 类型 获取全部的 value
     * 回调时进行数据的保存
     * 并设置时间 24H
     *
     * @param key
     * @param callBack
     * @return
     */
    public Object getHashValues(String key, CallBack callBack) {
        if (key == null) {
            return null;
        }
        if (this.hasKey(key)) { // 会存在为空的时候
//            List<Map<String, String>> values = redisTemplate.opsForHash().values(key);
            return redisTemplate.opsForHash().entries(key);
        }
        Object object = callBack.execute();
        if (object != null) {
            redisTemplate.opsForHash().putAll(key, (Map) object);
            // 过期时间
            redisTemplate.expire(key, 24, TimeUnit.HOURS);
        }
        return object;
    }

    //-------------------------------------------------------------------------------
    //  华丽丽的分割线
    //*******************************************************************************
    //  集合操作
    //-------------------------------------------------------------------------------


    /**
     * 同一key 新增值
     *
     * @param key
     * @param secondKey
     * @param value
     * @return
     */
    public boolean put24H(String key, Object secondKey, Object value) {
        return this.put(key, secondKey, value, HOUR24);
    }

    /**
     * 同一key 新增值(理论上，禁止存无过期时间的值)
     *
     * @param key
     * @param secondKey
     * @param value
     * @return
     */
    private boolean put(String key, Object secondKey, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, secondKey, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }

            log.info("保存长度：【{}】【{}】【{}】", this.values(key).size(), key, secondKey);

            return true;
        } catch (Exception e) {
            log.error("redis操作错误",e);
            return false;
        }
    }

    /**
     * 获取值
     *
     * @param key
     * @param secondKey
     * @return
     */
    public Object get(String key, Object secondKey) {
        try {
            return key == null || secondKey == null ? null : redisTemplate.opsForHash().get(key, secondKey);
        } catch (Exception e) {
            log.error("redis操作错误",e);
            return false;
        }
    }

    /**
     * 是否有此值
     *
     * @param key
     * @param secondKey
     * @return
     */
    public boolean hasKey(String key, Object secondKey) {
        try {
            return redisTemplate.opsForHash().hasKey(key, secondKey);
        } catch (Exception e) {
            log.error("redis操作错误",e);
            return false;
        }
    }

    /**
     * 获取批量数据
     *
     * @param key
     * @return
     */
    public List<Object> values(String key) {
        if (key == null) {
            return null;
        }
        return redisTemplate.opsForHash().values(key);
    }


    //-------------------------------------------------------------------------------
    //  华丽丽的分割线
    //*******************************************************************************
    //  公共操作
    //-------------------------------------------------------------------------------

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 全部清除
     */
    public void clear() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }


}

package com.lbj.common.client.base;


import com.lbj.common.client.ret.PageCallback;
import com.lbj.common.client.util.PageUtils;
import com.lbj.common.client.util.UserUtil;
import com.lbj.common.core.config.ErrorCode;
import com.lbj.common.core.ret.CommRes;
import com.lbj.common.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<T> {


    //由子类来具体的实现DAO
    protected abstract BaseDao<T> getBaseDAO();

    // -------------------------------------------------------------------------------------
    // 查询相关
    // -------------------------------------------------------------------------------------

    /**
     * 多条件查询
     *
     * @param t
     * @return
     */
    public List<T> find(T t) {
        if (t == null) {
            return null;
        }
        List<T> list = this.getBaseDAO().find(t);
//        list = list == null ? new ArrayList<>() : list;
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * 多条件分页
     *
     * @return
     */
    public CommRes find(T t, Integer pageNum, Integer pageSize) {
        return PageUtils.find(pageNum, pageSize, new PageCallback() {
            @Override
            public Object execute() {
                return find(t);
            }
        });
    }

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    public CommRes<T> getById(String id) {
        T t = this.getBaseDAO().getbyId(id);
        return new CommRes<>(ErrorCode.SUCCESS, t);
    }

    // -------------------------------------------------------------------------------------
    // 插入相关
    // -------------------------------------------------------------------------------------

    /**
     * 插入数据
     *
     * @param t
     * @return
     */
    public CommRes<String> insert(T t) {
        BaseEntity baseEntity = this.getBaseEntity(t);
        baseEntity.setCreateTime(TimeUtil.getCurTime());
        baseEntity.setCreateUser(UserUtil.getCurUserId());
        baseEntity.setUpdateTime(TimeUtil.getCurTime());
        baseEntity.setUpdateUser(UserUtil.getCurUserId());
        int result = this.getBaseDAO().insert(t);
        if (result < 1) {
            return new CommRes<>(ErrorCode.FailureInsert, baseEntity.getId());
        }
        return new CommRes<>(ErrorCode.SUCCESS, baseEntity.getId());

    }

    // -------------------------------------------------------------------------------------
    // 修改相关
    // -------------------------------------------------------------------------------------

    /**
     * 更新数据
     *
     * @param t
     * @return
     */
    public CommRes<String> update(T t) {
        BaseEntity baseEntity = this.getBaseEntity(t);
        baseEntity.setUpdateTime(TimeUtil.getCurTime());
        baseEntity.setUpdateUser(UserUtil.getCurUserId());
        int result = this.getBaseDAO().update(t);
        if (result < 1) {
            return new CommRes<>(ErrorCode.FailureUpdate, baseEntity.getId());
        }
        return new CommRes<>(ErrorCode.SUCCESS, baseEntity.getId());

    }


    // -------------------------------------------------------------------------------------
    // 删除相关
    // -------------------------------------------------------------------------------------

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public CommRes<String> remove(String ids) {
        CommRes<String> ret = null;
        String[] aa = ids.split(",");
        for (String id : aa) {
            ret = this.removeOne(id);
            if (!ret.getCode().equals(ErrorCode.SUCCESS.getCode())) {
                // 删除失败
                return ret;
            }
        }
        return new CommRes<>(ErrorCode.SUCCESS);

    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    public CommRes<String> removeOne(String id) {
        CommRes<String> ret = null;
        T t = this.getBaseDAO().getbyId(id);
        if (t == null) {
            return new CommRes<>(ErrorCode.NoDataById, id);
        } else {
            ret = this.removeOne(t);
            if (!ret.getCode().equals(ErrorCode.SUCCESS.getCode())) {
                // 删除失败
                return ret;
            }
        }
        return new CommRes<>(ErrorCode.SUCCESS, id);
    }

    /**
     * 删除单个
     *
     * @param t
     * @return
     */
    public CommRes<String> removeOne(T t) {
        if (t == null) {
            return new CommRes<>(ErrorCode.NoDataById);
        } else {
            BaseEntity baseEntity = this.getBaseEntity(t);
            baseEntity.setUpdateTime(TimeUtil.getCurTime());
            baseEntity.setUpdateUser(UserUtil.getCurUserId());
            boolean result = this.getBaseDAO().remove(t);
            if (!result) {
                return new CommRes<>(ErrorCode.FailureRemove, baseEntity.getId());
            }
            return new CommRes<>(ErrorCode.SUCCESS, baseEntity.getId());
        }
    }


    // -------------------------------------------------------------------------------------
    // 私有函数
    // -------------------------------------------------------------------------------------

    /**
     * 泛型转BaseEntity
     *
     * @param t
     * @return
     */
    private BaseEntity getBaseEntity(T t) {
        return (BaseEntity) t;
    }

}

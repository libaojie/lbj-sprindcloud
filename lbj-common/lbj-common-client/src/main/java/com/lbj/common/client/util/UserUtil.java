package com.lbj.common.client.util;


import com.lbj.common.client.base.BaseContextHandler;
import com.lbj.common.client.base.BaseEntity;
import com.lbj.common.core.constants.Constants;
import com.lbj.common.core.constants.Context;
import com.lbj.common.util.TimeUtil;
import com.lbj.common.util.type.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(UserUtil.class);

    /**
     * 获取当前操作用户
     *
     * @return
     */
    public static String getCurUserId() {
        String userId = BaseContextHandler.getStr(Context.userId);
        if (StringUtil.isNull(userId)) {
            return Constants.DefaultUser;
        } else {
            return userId;
        }
    }

    public static void initUpdateUser(BaseEntity baseEntity) {
        //判空
        if (baseEntity == null) return;
        String userName = getCurUserId();
        if (StringUtil.isNull(baseEntity.getCurrUser())) baseEntity.setCurrUser(userName);
        if (StringUtil.isNull(baseEntity.getUpdateUser())) baseEntity.setUpdateUser(userName);
        baseEntity.setUpdateTime(TimeUtil.getCurTime());
    }

    public static void initUser(BaseEntity baseEntity) {
        //判空
        if (baseEntity == null) return;
        String userName = getCurUserId();
        if (StringUtil.isNull(baseEntity.getCreateUser())) baseEntity.setCreateUser(userName);
        if (StringUtil.isNull(baseEntity.getCurrUser())) baseEntity.setCurrUser(userName);
        baseEntity.setCreateTime(TimeUtil.getCurTime());
        initUpdateUser(baseEntity);
    }
}

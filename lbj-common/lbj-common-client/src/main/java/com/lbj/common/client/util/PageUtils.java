package com.lbj.common.client.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hirisun.common.core.config.ErrorCode;
import com.hirisun.common.core.ret.CommRes;
import com.lbj.common.client.ret.PageCallback;
import com.lbj.common.client.ret.PageCommRes;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static CommRes find(Integer pageNum, Integer pageSize, PageCallback pageCallback) {
        if (pageNum == null || pageNum == 0) {
            // 无分页
            Object object = pageCallback.execute();
            object = object == null ? new ArrayList<>() : object;
            return new CommRes(ErrorCode.SUCCESS, object);
        } else {
            // 有分页
            if (pageSize == null || pageSize == 0) {
                pageSize = 10;
            }
            PageHelper.startPage(pageNum, pageSize);
            Object object = pageCallback.execute();
            object = object == null ? new ArrayList<>() : object;
            PageInfo pageInfo = new PageInfo((List) object);
            return new PageCommRes<>(ErrorCode.SUCCESS, pageInfo);
        }
    }
}

package com.lbj.common.client.base;

import com.hirisun.common.core.constants.Context;
import com.hirisun.common.util.TimeUtil;
import com.hirisun.common.util.UuidUtil;
import com.hirisun.common.util.type.StringUtil;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BaseEntity {

    protected int pNum;
    protected int pSize = 0;// 默认为0表示查询全部
    @ApiModelProperty(value = "ID", name = "id", example = "")
    protected String id;
    @ApiModelProperty(value = "删除标记", name = "", example = "")
    protected String delFlag;
    @ApiModelProperty(value = "创建者", name = "", example = "")
    protected String createUser;
    @ApiModelProperty(value = "创建时间", name = "", example = "")
    protected Date createTime;
    @ApiModelProperty(value = "修改者", name = "", example = "")
    protected String updateUser;
    @ApiModelProperty(value = "修改时间", name = "", example = "")
    protected Date updateTime;
    @ApiModelProperty(value = "注释", name = "", example = "")
    protected String remark;
    @ApiModelProperty(value = "操作者", name = "", example = "")
    protected String currUser;


    public BaseEntity() {
    }

    public void initData() {
        this.id = UuidUtil.getUUID32();
        this.createTime = TimeUtil.getCurTime();
        this.updateTime = TimeUtil.getCurTime();

        String userId = BaseContextHandler.getStr(Context.userId);
        if (StringUtil.isNull(userId)) {
            this.createUser = "";
            this.updateUser = "";
        } else {
            this.createUser = userId;
            this.updateUser = userId;
        }
    }

    public void initDataUpdate() {
        this.updateTime = TimeUtil.getCurTime();

        String userId = BaseContextHandler.getStr(Context.userId);
        if (StringUtil.isNull(userId)) {
            this.updateUser = "";
        } else {
            this.updateUser = userId;
        }
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCurrUser() {
        return currUser;
    }

    public void setCurrUser(String currUser) {
        this.currUser = currUser;
    }

    public int getpNum() {
        return pNum;
    }


    public void setpNum(int pNum) {
        this.pNum = pNum;
    }


    public int getpSize() {
        return pSize;
    }


    public void setpSize(int pSize) {
        this.pSize = pSize;
    }
}

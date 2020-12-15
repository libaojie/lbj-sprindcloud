package com.lbj.feign.auth.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 鉴权返回结果
 */
public class RepCommon implements Serializable {
    private static final long serialVersionUID = 1L; //序列化

    @ApiModelProperty(value = "用户id", name = "userId", example = "")
    private String userId;
    @ApiModelProperty(value = "用户名", name = "userName", example = "")
    private String userName;
    @ApiModelProperty(value = "登录名", name = "loginName", example = "")
    private String loginName;
    @ApiModelProperty(value = "返回信息", name = "msg", example = "")
    private String msg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public RepCommon(String json) throws JsonProcessingException {
        RepCommon param = null;
        param = new ObjectMapper().readValue(json, RepCommon.class);
        this.userId=param.getUserId();
        this.userName = param.getUserName();
        this.loginName = param.getLoginName();
        this.msg = param.getMsg();
    }

}

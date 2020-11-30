package com.lbj.common.core.constants;

public class Context {

;
    // 当前用户(认证中心专用，用于一次查找，鉴权全局使用)
    public static final String user = "user";
    // 本次访问审计日志id
    public static final String sysLogId = "sysLogId";

    // 当前用户信息（适用于其他client）
    public static final String userId = "userid";
    public static final String userName = "username";
    public static final String loginName = "loginname";
}

package com.lbj.common.core.interceptor;


import com.lbj.common.core.config.ErrorCode;

/**
 * @author 翟盛龙
 * @version 1.1
 * @describe 自定义异常类，继承运行时异常类，且将自己的Errorcode 作为私有属性
 */
public class MyException extends RuntimeException {
    private ErrorCode errorCode;

    // 讲道理重写构造方法，必须有默认的构造方法
    public MyException(){

    }
    // 构造方法
    public MyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
package com.lbj.common.core.interceptor;


import com.lbj.common.core.config.ErrorCode;

public class MyException extends RuntimeException {
    private ErrorCode errorCode;

    // 讲道理重写构造方法，必须有默认的构造方法
    public MyException() {

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
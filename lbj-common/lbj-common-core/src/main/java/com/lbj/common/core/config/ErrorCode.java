package com.lbj.common.core.config;



public enum ErrorCode implements IErrorCodeEnum {

	SUCCESS(0,"成功"),
	FAILURE(1,"失败"),
    EXCEPTION(2,"系统异常"),

	FailureInsert(101001,"增加失败"), 
	FailureUpdate(101002,"修改失败"),
	FailureRemove(101003,"删除失败"),
	FailureSelect(101004,"查询失败"),
    IdNotFound(101005, "id未传入"),
    NoDataById(101006, "根据id无法找到此项"),

    NOT_QUERY_TYPE_PARAM(200101, "未接收到查询类型参数"),
    QUERY_TYPE_MISMATCH(200102, "查询类型不匹配"),
    PARAM_LACK(200103, "缺少必要参数");

    // 成员变量
    private String msg;
    private int code;

    // 构造方法
    ErrorCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    // 覆盖方法
    @Override
    public String toString() {
        return this.code + "_" + this.msg;
    }
    
//    @Override
    public String getCode(){
    	return String.valueOf(this.code);
    }
    
//    @Override
    public String getMsg(){
    	return this.msg;
    }
}

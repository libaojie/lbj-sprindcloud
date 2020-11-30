package com.lbj.common.core.ret;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel(value = "通用返回结果", description = "")
public class CommRes<T> implements Serializable {

	private static final long serialVersionUID = 1L; //序列化

	@ApiModelProperty(value = "返回状态码", name = "code", example = "1", notes = "0:成功；非：失败")
	private String code = "0";
	@ApiModelProperty(value = "返回信息", name = "msg", example = "", notes = "")
	private String msg = "success";

	@ApiModelProperty(value = "返回数据", name = "data", example = "", notes = "")
	private T data;

	public CommRes() {
	}

	public CommRes(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public CommRes(IErrorCodeEnum errorCode) {
		this.code = String.valueOf(errorCode.getCode());
		this.msg = errorCode.getMsg();
	}

	public CommRes(IErrorCodeEnum errorCode, T data) {
		this.code = String.valueOf(errorCode.getCode());
		this.msg = errorCode.getMsg();
		this.data = data;
	}

//	public CommRes(ErrorConfig errorConfig, ErrorCode code, String msg,
//			T data) {
//		this.code = errorConfig.getCode(code);
//		this.msg = errorConfig.getMsg(code);
//		this.data = data;
//	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


	
	
}

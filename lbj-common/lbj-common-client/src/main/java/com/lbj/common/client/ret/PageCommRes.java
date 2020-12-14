package com.lbj.common.client.ret;

import com.github.pagehelper.PageInfo;
import com.hirisun.common.core.config.IErrorCodeEnum;
import com.hirisun.common.core.ret.CommRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "分页返回结果", description = "")
public class PageCommRes<T> extends CommRes<T> implements Serializable {

	private static final long serialVersionUID = 1L; //序列化

	@ApiModelProperty(value = "总页数", name = "pages", example = "16")
	private int pages;
	@ApiModelProperty(value = "总记录数", name = "total", example = "2000")
	private long total;
//	@ApiModelProperty(value = "页码", name = "pageNum", example = "2")
//	private int pageNum;
	@ApiModelProperty(value = "页码", name = "page", example = "2")
	private int page;
	@ApiModelProperty(value = "当前页记录数", name = "size", example = "18")
	private int size;
//	@ApiModelProperty(value = "每页记录数", name = "pageSize", example = "20")
//	private int pageSize;
	@ApiModelProperty(value = "每页记录数", name = "perPage", example = "20")
	private int perPage;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public PageCommRes(){}

	public PageCommRes(IErrorCodeEnum errorCode, PageInfo pageInfo) {
		super.setCode(String.valueOf(errorCode.getCode()));
		super.setMsg(errorCode.getMsg());
		super.setData((T) pageInfo.getList());
		this.setPages(pageInfo.getPages());// 总页数
		this.setTotal(pageInfo.getTotal());// 总记录数
//		this.setPageNum(pageInfo.getPageNum());// 页码
//		this.setPageSize(pageInfo.getPageSize());// 每页记录数
//		this.setPageNum(pageInfo.getPageNum());// 当前页
		this.setPage(pageInfo.getPageNum());// 当前页
		this.setSize(pageInfo.getSize());// 当前页记录数
		this.setPerPage(pageInfo.getPageSize());// 每页记录数
		this.setTotal(pageInfo.getTotal());// 总记录数


	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

//	public int getPageNum() {
//		return pageNum;
//	}
//
//	public void setPageNum(int pageNum) {
//		this.pageNum = pageNum;
//	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
}

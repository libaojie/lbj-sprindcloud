package com.lbj.common.client.base;

import org.springframework.beans.factory.annotation.Autowired;


public class BaseController<T> {

	@Autowired
	protected T tService;
}


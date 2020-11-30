package com.lbj.common.util;

import java.util.UUID;

public class UuidUtil {

	/**
	 * 获取32位uuid
	 * @return
	 */
	public static String getUUID32() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
}

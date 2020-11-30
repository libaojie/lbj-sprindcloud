package com.lbj.common.util.redis;

/**
 * The class Redis key util.
 *
 * @author paascloud.net@gmail.com
 */
public class RedisKeyUtil {


	public static String getKey(String project, String type, String value) {
		return project + ":" + type + ":" + value;

	}


}

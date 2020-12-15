package com.lbj.common.util.type;


import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;


public class StringUtil {

	public static boolean isNull(String value) {
		return StringUtils.isEmpty(value);
	}

	public static boolean isNotBlank(String str) {
		return str != null && !str.equals("");
	}
	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	public static boolean isBlank(String str) {
		return str == null || str.equals("");
	}

	public static String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String listToString(List<String> list, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i < list.size() - 1) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}


	/**
	 * String是否相等
	 *
	 * @param left
	 * @param right
	 * @return
	 */
	public static boolean isEquals(String left, String right) {
		if (left == null && right == null) {
			return true;
		} else if (left != null && right != null) {
			return left.equals(right);
		} else {
			return false;
		}
	}

}

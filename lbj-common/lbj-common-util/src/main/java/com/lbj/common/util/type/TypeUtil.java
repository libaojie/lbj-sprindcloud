package com.lbj.common.util.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TypeUtil {
//	private static final Logger logger = LoggerFactory
//			.getLogger(TypeUtil.class);

	/**
	 * 格式转化
	 * @param value
	 * @return
	 */
	public static String date2Str(Date value){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(value);
	}

	/**
	 * str转化int
	 * @param value
	 * @return
	 */
	public static Integer str2int(String value) {
		if (value == null) {
			return null;
		}
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			// 异常处理
			log.error("str转化int失败"+value);
			return null;
		}
	}

	/**
	 * String转Date
	 * @param value
	 * @return
	 */
	public static Date str2Date(String value){
		if (value == null){
			return null;
		}

		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date  = format.parse(value);
			return date;

		} catch (Exception e) {
			// 异常处理
			log.error("str转化date失败"+value);
			return null;
		}
	}

	public static Date str2Date(String value, String pattern){
		if(value == null){
			return null;
		}
		if(StringUtil.isEmpty(pattern)){
			return null;
		}
		try {
			DateFormat format = new SimpleDateFormat(pattern);
			Date date  = format.parse(value);
			return date;
		} catch (Exception e) {
			// 异常处理
			log.error("str转化date失败"+value);
			return null;
		}
	}



	public static String obj2json(Object object){
		return JSON.toJSONString(object, SerializerFeature.PrettyFormat,SerializerFeature.WriteClassName,SerializerFeature.WriteMapNullValue);
	}

	public static JSONObject str2json(String content)
	{
		JSONObject object = JSON.parseObject(content);
		return object;
	}


}

package com.lbj.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public static Date getCurTime() {
		return new Date();
	}
	
	public static long getCurTimeMillis(){
		return System.currentTimeMillis();
	}

	public static int getDifference(Date newDate, Date oldDate){
		long newTime = newDate.getTime();
		long oldTime = oldDate.getTime();
		int second = (int) ((newTime - oldTime) / (1000));
		return second;
	}

	public static String nowMilliseconds() {
		Date currentTime = new Date();
		return String.valueOf(currentTime.getTime());
	}

	//格式化日期
	public static String formatNow(String formatStr) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		return formatter.format(currentTime);
	}

	/**
	 * 日期转换，yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatNow() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(currentTime);
	}

	//格式化日期
	public static String format(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	//格式化日期
	public static String format(Date date, String formatStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		return formatter.format(date);
	}

	//字符串转日期
	public static Date getDate(String date, String formatStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		try {
			if(date == null) {
				return null;
			}
			Date d = formatter.parse(date);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//日期偏移
	public static Date add(String date, int field, int offset) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatter.parse(date));
		cal.add(field, offset);
		return cal.getTime();
	}

	//日期偏移
	public static Date add(Date date, int field, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, offset);
		return cal.getTime();
	}

	//上个小时
	public static Date getLastHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, -1);
		return cal.getTime();
	}

	//上一分钟
	public static Date getLastMinute() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, -1);
		return cal.getTime();
	}

	//昨天
	public static Date getLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	//上周一
	public static Date getLastWeekMonday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday());
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}

	//上周五
	public static Date getLastWeekFriday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday());
		cal.add(Calendar.DATE, -3);
		return cal.getTime();
	}

	//上周日
	public static Date getLastWeekSunday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday());
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	//本周一
	public static Date getThisWeekMonday() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	//下周一
	public static Date getNextWeekMonday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday());
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}

	//上月第一天
	public static Date getLastMonthFirstDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	//上月最后一天
	public static Date getLastMonthLastDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	//去年第一天
	public static Date getLastYearFirstDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}

	//去年最后一天
	public static Date getLastYearLastDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	//上季度第一天
	public static Date getLastQuarterFirstDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int month = cal.get(Calendar.MONTH) + 1;
		if(1 <= month && month <= 3) {
			cal.set(Calendar.MONTH, 9);
			cal.add(Calendar.YEAR, -1);
		}
		else if(4 <= month && month <= 6) {
			cal.set(Calendar.MONTH, 0);
		}
		else if(7 <= month && month <= 9) {
			cal.set(Calendar.MONTH, 3);
		}
		else {
			cal.set(Calendar.MONTH, 6);
		}
		return cal.getTime();
	}

	//上季度最后一天
	public static Date getLastQuarterLastDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int month = cal.get(Calendar.MONTH) + 1;
		if(1 <= month && month <= 3) {
			cal.set(Calendar.MONTH, 0);
			cal.add(Calendar.DATE, -1);
		}
		else if(4 <= month && month <= 6) {
			cal.set(Calendar.MONTH, 3);
			cal.add(Calendar.DATE, -1);
		}
		else if(7 <= month && month <= 9) {
			cal.set(Calendar.MONTH, 6);
			cal.add(Calendar.DATE, -1);
		}
		else {
			cal.set(Calendar.MONTH, 9);
			cal.add(Calendar.DATE, -1);
		}
		return cal.getTime();
	}

	public static String nowDateFormat(String  pattern) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * @Description: long类型转换成日期
	 *
	 * @param lo 毫秒数
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String longToDate(long lo){
		Date date = new Date(lo);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(date);
	}


}

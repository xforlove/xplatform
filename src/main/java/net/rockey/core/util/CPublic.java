package net.rockey.core.util;

import java.text.SimpleDateFormat;
import java.util.List;

public class CPublic {

	public static String parseStr(Object info) {
		if (info == null) {
			return null;
		} else if (info instanceof String) {
			return (String) info;
		} else {
			return info.toString();
		}
	}

	/**
	 * 判断一个字符串是否在数组内
	 * 
	 * @param ss
	 * @param el
	 * @return
	 */
	public static boolean isExists(String[] ss, String el) {
		for (int i = 0; i < ss.length; i++) {
			if (el.equals(ss[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断一个字符串是否在集合内
	 * 
	 * @param list
	 * @param el
	 * @return
	 */
	public static boolean isExists(List<String> list, String el) {
		for (String str : list) {
			if (el.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get Current Date
	 * 
	 * Format :YYYYMMDD
	 * 
	 * Return Format Date
	 */

	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		java.util.Date dateCurrent = new java.util.Date();

		String strCurrentDate = formatter.format(dateCurrent);

		return strCurrentDate;
	}

	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		java.util.Date dateCurrent = new java.util.Date();

		String strCurrentTime = formatter.format(dateCurrent);

		return strCurrentTime;
	}

	public static String getDateAndTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date dateCurrent = new java.util.Date();

		String strCurrentTime = formatter.format(dateCurrent);

		return strCurrentTime;
	}

}

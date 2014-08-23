package net.rockey.core.util;

import java.util.Map;

public class ParamUtils {

	/**
	 * 返回参数字符串内容，异常情况下返回空字符串
	 * 
	 * @param parameterMap
	 * @param key
	 * @return
	 */
	public static String getString(Map<String, Object> parameterMap, String key) {
		if (parameterMap == null) {
			return "";
		}

		if (!parameterMap.isEmpty()) {
			return (String) parameterMap.get(key);
		} else {
			return "";
		}
	}
}

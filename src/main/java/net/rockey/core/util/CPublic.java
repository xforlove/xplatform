package net.rockey.core.util;

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

}

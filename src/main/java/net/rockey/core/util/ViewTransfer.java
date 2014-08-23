package net.rockey.core.util;

public class ViewTransfer {

	public static final String getPairStatFlagCn(String in) {
		if ("NORMAL".equals(in)) {
			return "启用";
		} else if ("CLOSE".equals(in)) {
			return "禁用";
		} else {
			return "未知";
		}
	}
}

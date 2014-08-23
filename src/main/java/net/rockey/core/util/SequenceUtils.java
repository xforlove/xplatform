package net.rockey.core.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SequenceUtils {

	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

	/**
	 * yy	- Year, 				eg: 14 
	 * MM 	- Month in year, 		eg: 07 
	 * dd 	- Day in month, 		eg: 01 
	 * HH 	- Hour in day(0-23), 	eg: 09 
	 * mm 	- Minute in hour, 		eg: 30 
	 * ss 	- Second in minute, 	eg: 45 
	 */
	private final static Format dateFormat = new SimpleDateFormat("yyMMddHHmmss");

	private final static NumberFormat numberFormat = new DecimalFormat("000");

	private static int seq = 1;

	private static final int MAX_VALUE = 999;
	
	/**
	 * 按照前缀，返回序列号
	 * 
	 * @param prefix
	 * @return
	 */
	public static synchronized Long getSequence(SequencePrefix prefix) {
		Calendar current = Calendar.getInstance();
		StringBuffer sb = new StringBuffer(prefix.getName());
		dateFormat.format(current.getTime(), sb, HELPER_POSITION);
		numberFormat.format(seq, sb, HELPER_POSITION);

		if (seq == MAX_VALUE) {
			seq = 1;
		} else {
			seq++;
		}
		return Long.parseLong(sb.toString());
	}
	
}

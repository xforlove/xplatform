package net.rockey.ext.export;

import java.util.ArrayList;
import java.util.List;

import net.rockey.core.util.LogUtils;
import net.rockey.core.util.ReflectUtils;

import org.apache.log4j.Logger;

public class TableModel {

	private final Logger log = LogUtils.getLogger(TableModel.class, true);
	private String name;
	private List<String> headers = new ArrayList<String>();
	private List data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addHeaders(String... header) {
		if (header == null) {
			return;
		}

		for (String text : header) {
			if (text == null) {
				continue;
			}

			headers.add(text);
		}
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getHeaderCount() {
		return headers.size();
	}

	public int getDataCount() {
		return data.size();
	}

	public String getHeader(int index) {
		return headers.get(index);
	}

	public String getValue(int i, int j) {
		try {
			String header = getHeader(j);
			Object object = data.get(i);
			String methodName = ReflectUtils
					.getGetterMethodName(object, header);
			Object value = ReflectUtils.getMethodValue(object, methodName);

			return (value == null) ? "" : value.toString();
		} catch (Exception ex) {
			log.info("error", ex);

			return "";
		}
	}
}

package net.rockey.form.manager;

import java.util.Map;

import net.rockey.core.hibernate.HibernateEntityDao;
import net.rockey.core.util.LogUtils;
import net.rockey.form.model.Record;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RecordManager extends HibernateEntityDao<Record> {

	private final Logger log = LogUtils.getLogger(RecordManager.class, true);

	/**
	 * 根据ref获得记录.
	 */
	public Record findByRef(String ref) {
		if (isBlank(ref)) {
			return null;
		}

		Record record = null;
		try {
			record = this.findUniqueBy("ref", ref);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}

		return record;
	}

	/**
	 * 获得string值.
	 */
	public String getStringValue(Map<String, Object> map, String name) {
		Object value = map.get(name);

		if (value == null) {
			return null;
		}

		if (value instanceof String) {
			return (String) value;
		}

		return value.toString();
	}

	/**
	 * 获得int值.
	 */
	public Integer getIntValue(Map<String, Object> map, String name) {
		Object value = map.get(name);

		if (value == null) {
			return null;
		}

		if (value instanceof Integer) {
			return (Integer) value;
		}

		return Integer.parseInt(value.toString());
	}

	/**
	 * 判断字符串是否为空.
	 */
	public boolean isBlank(String text) {
		return (text == null) || "".equals(text);
	}
}

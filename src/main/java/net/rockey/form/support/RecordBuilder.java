package net.rockey.form.support;

import java.util.Collections;
import java.util.Map;

import net.rockey.core.util.CPublic;
import net.rockey.form.keyvalue.Prop;
import net.rockey.form.keyvalue.Record;

public class RecordBuilder {

	public Record build(Record record, String status,
			Map<String, Object> parameters) {
		record.setStatus(status);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			String value = CPublic.parseStr(entry.getValue());

			if ((value == null) || (value.trim().length() == 0)) {
				continue;
			}

			Prop prop = new Prop();
			prop.setCode(key);
			prop.setValue(value);
			record.getProps().put(prop.getCode(), prop);
		}

		return record;
	}

	/**
	 * 创建一个新record
	 */
	public Record build(String category, String status,
			Map<String, Object> parameters, String creator) {
		Record record = new Record();
		record.setCategory(category);
		record.setCreator(creator);
		record.setCreateTime(CPublic.getDateAndTime());

		return build(record, status, parameters);
	}

	/**
	 * 更新record的ref属性.
	 */
	public Record build(Record record, String status, String ref) {
		record.setRef(ref);

		return build(record, status, Collections.EMPTY_MAP);
	}

	/**
	 * 主要是获得多值属性，比如checkbox.
	 */
	public String getValue(String[] values) {
		if ((values == null) || (values.length == 0)) {
			return "";
		}

		if (values.length == 1) {
			return values[0];
		}

		StringBuilder buff = new StringBuilder();

		for (String value : values) {
			buff.append(value).append(",");
		}

		buff.deleteCharAt(buff.length() - 1);

		return buff.toString();
	}

}

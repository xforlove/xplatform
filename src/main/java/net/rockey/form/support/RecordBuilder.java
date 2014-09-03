package net.rockey.form.support;

import java.util.Collections;
import java.util.Map;

import net.rockey.core.util.CPublic;
import net.rockey.form.model.Prop;
import net.rockey.form.model.Record;

public class RecordBuilder {

	public Record build(Record record, String statFlag,
			Map<String, String[]> parameters) {
		record.setStatFlag(statFlag);

		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String key = entry.getKey();
			String[] value = entry.getValue();

			if ((value == null) || (value.length == 0)) {
				continue;
			}

			Prop prop = new Prop();
			prop.setCode(key);
			prop.setValue(this.getValue(value));
			record.getProps().put(prop.getCode(), prop);
		}

		return record;
	}

	/**
	 * 创建一个新record
	 */
	public Record build(String category, String statFlag,
			Map<String, String[]> parameters, Long creator) {
		Record record = new Record();
		record.setCategory(category);
		record.setCreator(creator);
		record.setCreateTime(CPublic.getDateAndTime());

		return build(record, statFlag, parameters);
	}

	/**
	 * 更新record的processInstanceId属性.
	 */
	public Record build(Record record, String statFlag, String processInstanceId) {
		record.setProcessInstanceId(processInstanceId);

		return build(record, statFlag, Collections.EMPTY_MAP);
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

package net.rockey.form.support;

import java.util.Collections;
import java.util.Map;

import net.rockey.core.util.CPublic;
import net.rockey.form.model.Prop;
import net.rockey.form.model.Record;

/**
 * 构建Record
 * 
 */
public class RecordBuilder {

	/** 可存入字段列表 */
	public static final String[] ALLOW_LIST = { "processDefinitionId",
			"bpmProcessId", "businessKey", "businessType",
			"autoCompleteFirstTask", "approver" };

	/**
	 * 把status和parameters更新到record里.
	 */
	public Record build(Record record, String statFlag,
			Map<String, Object> parameters) {
		record.setStatFlag(statFlag);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (!CPublic.isExists(ALLOW_LIST, key))
				continue;

			if ((value == null))
				continue;

			Prop prop = new Prop();
			prop.setCode(key);
			prop.setType(0);
			prop.setValue(CPublic.parseStr(value));
			prop.setRecord(record);

			record.getProps().add(prop);
		}

		return record;
	}

	/**
	 * 创建一个新record
	 */
	public Record build(String category, String statFlag,
			Map<String, Object> parameters, Long userId) {
		Record record = new Record();
		record.setCategory(category);
		record.setCreator(userId);
		return build(record, statFlag, parameters);
	}

	/**
	 * 更新record的ref属性.
	 */
	public Record build(Record record, String statFlag, String ref) {
		record.setRef(ref);

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

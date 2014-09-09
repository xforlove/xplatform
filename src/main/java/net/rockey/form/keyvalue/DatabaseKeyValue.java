package net.rockey.form.keyvalue;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

@Transactional
public class DatabaseKeyValue implements KeyValue {

	private static final Logger log = LoggerFactory
			.getLogger(DatabaseKeyValue.class);

	private JdbcTemplate jdbcTemplate;

	/**
	 * 根据code获得记录
	 */
	@Override
	public Record findByCode(String code) {
		if (isBlank(code)) {
			return null;
		}

		Record record = null;

		try {
			Map<String, Object> map = jdbcTemplate.queryForMap(
					"select * from KV_RECORD where id=?", code);
			record = convertRecord(map);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}

		return record;
	}

	/**
	 * 根据ref获得记录
	 */
	@Override
	public Record findByRef(String ref) {
		if (isBlank(ref)) {
			return null;
		}

		Record record = null;

		try {
			Map<String, Object> map = jdbcTemplate.queryForMap(
					"select * from KV_RECORD where ref=?", ref);
			record = convertRecord(map);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}

		return record;
	}

	/**
	 * 如果code为null，就执行insert，否则执行update.
	 */
	@Override
	public void save(Record record) {
		if (record.getCode() == null) {
			insert(record);
		} else {
			update(record);
		}
	}

	/**
	 * 根据code删除记录.
	 */
	@Override
	public void removeByCode(String code) {
		jdbcTemplate.update("delete from KV_RECORD where id=", code);
	}

	// ~ ======================================================================
	/**
	 * 把map转换成Record.
	 */
	public Record convertRecord(Map<String, Object> recordMap) {
		Record record = new Record();
		record.setCode(getStringValue(recordMap, "id"));
		record.setCategory(getStringValue(recordMap, "category"));
		record.setStatus(getStringValue(recordMap, "status"));
		record.setRef(getStringValue(recordMap, "ref"));
		record.setCreateTime(getStringValue(recordMap, "create_time"));
		record.setCreator(getStringValue(recordMap, "creator"));

		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select * from KV_PROP where record_id=?", record.getCode());

		for (Map<String, Object> propMap : list) {
			Prop prop = new Prop();
			prop.setCode(getStringValue(propMap, "code"));
			prop.setType(getStringValue(propMap, "type"));
			prop.setValue(getStringValue(propMap, "value"));
			record.getProps().put(prop.getCode(), prop);
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
	 * 获得date值.
	 */
	public Date getDateValue(Map<String, Object> map, String name) {
		Object value = map.get(name);

		if (value == null) {
			return null;
		}

		if (value instanceof Date) {
			return (Date) value;
		}

		return null;
	}

	/**
	 * 新建一条数据.
	 */
	public void insert(Record record) {
		String sqlRecordInsert = "insert into KV_RECORD(category,status,ref,create_time,creator) values(?,?,?,?,?)";
		String originalRef = record.getRef();
		String ref = originalRef;
		String createTime = record.getCreateTime();
		String creator = record.getCreator();

		if (originalRef == null) {
			ref = UUID.randomUUID().toString();
		}

		jdbcTemplate.update(sqlRecordInsert, record.getCategory(),
				record.getStatus(), ref, createTime, creator);

		Record resultRecord = findByRef(ref);
		String code = resultRecord.getCode();

		if (originalRef == null) {
			String sqlRecordUpdate = "update KV_RECORD set ref=null where id=?";
			jdbcTemplate.update(sqlRecordUpdate, code);
		}

		record.setCode(resultRecord.getCode());

		String sqlProp = "insert into KV_PROP(code,type,value,record_id) values(?,?,?,?)";

		for (Prop prop : record.getProps().values()) {
			jdbcTemplate.update(sqlProp, prop.getCode(), prop.getType(),
					prop.getValue(), record.getCode());
		}
	}

	/**
	 * 更新一条数据.
	 */
	public void update(Record record) {
		String sqlRecord = "update KV_RECORD set category=?,status=?,ref=? where id=?";
		jdbcTemplate.update(sqlRecord, record.getCategory(),
				record.getStatus(), record.getRef(), record.getCode());

		Record resultRecord = findByCode(record.getCode());
		String sqlPropInsert = "insert into KV_PROP(code,type,value,record_id) values(?,?,?,?)";
		String sqlPropUpdate = "update KV_PROP set type=?,value=? where code=? and record_id=?";

		for (Prop prop : record.getProps().values()) {
			// only append, won't delete
			if (resultRecord.getProps().containsKey(prop.getCode())) {
				jdbcTemplate.update(sqlPropUpdate, prop.getType(),
						prop.getValue(), prop.getCode(), record.getCode());
			} else {
				jdbcTemplate.update(sqlPropInsert, prop.getCode(),
						prop.getType(), prop.getValue(), record.getCode());
			}
		}
	}

	/**
	 * 判断字符串是否为空.
	 */
	public boolean isBlank(String text) {
		return (text == null) || "".equals(text);
	}

	// ~ =================================================================
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

package net.rockey.form.keyvalue;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Record implements Serializable {

	private Long id;
	private String code;
	private String category;
	private String status;
	private String ref;
	private String creator;
	private String createTime;

	private Map<String, Prop> props = new LinkedHashMap<String, Prop>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Map<String, Prop> getProps() {
		return props;
	}

	public void setProps(Map<String, Prop> props) {
		this.props = props;
	}
}

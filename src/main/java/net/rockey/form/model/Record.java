package net.rockey.form.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "x_record")
public class Record implements Serializable {

	private Long id;
	private String code;
	private String category;
	private String processInstanceId;
	private String statFlag;
	private Long creator;
	private String createTime;

	private Map<String, Prop> props = new LinkedHashMap<String, Prop>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code", length = 64)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "category", length = 64)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "process_instance_id", length = 64)
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Column(name = "stat_flag", length = 64)
	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

	@Column(name = "creator")
	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Column(name = "create_time", length = 64)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@OneToMany(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Map<String, Prop> getProps() {
		return props;
	}

	public void setProps(Map<String, Prop> props) {
		this.props = props;
	}

}

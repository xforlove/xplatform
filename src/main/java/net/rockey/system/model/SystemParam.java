package net.rockey.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_param")
public class SystemParam {

	private String sparamCode;
	private String sparamName;
	private String sparamType;
	private String sparamValue;
	private String sparamDefault;
	private String statFlag;
	private String isSplimit;
	private String sparamDescn;
	private String createTime;
	private String updateTime;

	@Id
	@Column(name = "sparam_code", unique = true, nullable = false, length = 50)
	public String getSparamCode() {
		return sparamCode;
	}

	public void setSparamCode(String sparamCode) {
		this.sparamCode = sparamCode;
	}

	@Column(name = "sparam_name", nullable = false, length = 200)
	public String getSparamName() {
		return sparamName;
	}

	public void setSparamName(String sparamName) {
		this.sparamName = sparamName;
	}

	@Column(name = "sparam_type", nullable = false, length = 20)
	public String getSparamType() {
		return sparamType;
	}

	public void setSparamType(String sparamType) {
		this.sparamType = sparamType;
	}

	@Column(name = "sparam_value", nullable = false, length = 200)
	public String getSparamValue() {
		return sparamValue;
	}

	public void setSparamValue(String sparamValue) {
		this.sparamValue = sparamValue;
	}

	@Column(name = "sparam_default", nullable = false, length = 200)
	public String getSparamDefault() {
		return sparamDefault;
	}

	public void setSparamDefault(String sparamDefault) {
		this.sparamDefault = sparamDefault;
	}

	@Column(name = "stat_flag", nullable = false, length = 50)
	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

	@Column(name = "is_splimit", nullable = false, length = 10)
	public String getIsSplimit() {
		return isSplimit;
	}

	public void setIsSplimit(String isSplimit) {
		this.isSplimit = isSplimit;
	}

	@Column(name = "sparam_descn", length = 200)
	public String getSparamDescn() {
		return sparamDescn;
	}

	public void setSparamDescn(String sparamDescn) {
		this.sparamDescn = sparamDescn;
	}

	@Column(name = "create_time", length = 14)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 14)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}

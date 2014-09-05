package net.rockey.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_param")
public class Sysparam {

	private Long id;

	/** 参数代号 */
	private String code;

	/** 参数名称 */
	private String name;

	/** 参数类型：1-布尔型, 2-整型, 3-双精度浮点型, 4-字符串 */
	private int type;

	/** 参数值 */
	private String value;

	/** 参数默认值 */
	private String defValue;

	/** 状态 */
	private String statFlag;

	/** 是否有特殊配置：0-无, 1-有 */
	private int isSplimit;

	/** 参数描述 */
	private String descn;

	private String createTime;
	private String updateTime;

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

	@Column(name = "name", length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "value", length = 256)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "def_value", length = 256)
	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	@Column(name = "stat_flag", length = 64)
	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

	@Column(name = "is_splimit")
	public int getIsSplimit() {
		return isSplimit;
	}

	public void setIsSplimit(int isSplimit) {
		this.isSplimit = isSplimit;
	}

	@Column(name = "descn", length = 256)
	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	@Column(name = "create_time", length = 64)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 64)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}

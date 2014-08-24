package net.rockey.bpm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BPM_CONF_FORM")
public class BpmConfForm implements java.io.Serializable {
	private static final long serialVersionUID = 0L;

	/** null. */
	private Long id;

	/** null. */
	private BpmConfNode bpmConfNode;

	/** null. */
	private String value;

	/** null. */
	private Integer type;

	/** null. */
	private String originValue;

	/** null. */
	private Integer originType;

	/** null. */
	private Integer status;

	public BpmConfForm() {
	}

	public BpmConfForm(BpmConfNode bpmConfNode, String value, Integer type,
			String originValue, Integer originType, Integer status) {
		this.bpmConfNode = bpmConfNode;
		this.value = value;
		this.type = type;
		this.originValue = originValue;
		this.originType = originType;
		this.status = status;
	}

	/** @return null. */
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            null.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** @return null. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_ID")
	public BpmConfNode getBpmConfNode() {
		return this.bpmConfNode;
	}

	/**
	 * @param bpmConfNode
	 *            null.
	 */
	public void setBpmConfNode(BpmConfNode bpmConfNode) {
		this.bpmConfNode = bpmConfNode;
	}

	/** @return null. */
	@Column(name = "VALUE", length = 200)
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value
	 *            null.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/** @return null. */
	@Column(name = "TYPE")
	public Integer getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            null.
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/** @return null. */
	@Column(name = "ORIGIN_VALUE", length = 200)
	public String getOriginValue() {
		return this.originValue;
	}

	/**
	 * @param originValue
	 *            null.
	 */
	public void setOriginValue(String originValue) {
		this.originValue = originValue;
	}

	/** @return null. */
	@Column(name = "ORIGIN_TYPE")
	public Integer getOriginType() {
		return this.originType;
	}

	/**
	 * @param originType
	 *            null.
	 */
	public void setOriginType(Integer originType) {
		this.originType = originType;
	}

	/** @return null. */
	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * @param status
	 *            null.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}

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
@Table(name = "BPM_CONF_NOTICE")
public class BpmConfNotice implements java.io.Serializable {
	private static final long serialVersionUID = 0L;

	/** null. */
	private Long id;

	/** null. */
	private BpmMailTemplate bpmMailTemplate;

	/** null. */
	private BpmConfNode bpmConfNode;

	/** null. */
	private Integer type;

	/** null. */
	private String receiver;

	/** null. */
	private String dueDate;

	public BpmConfNotice() {
	}

	public BpmConfNotice(BpmMailTemplate bpmMailTemplate,
			BpmConfNode bpmConfNode, Integer type, String receiver,
			String dueDate) {
		this.bpmMailTemplate = bpmMailTemplate;
		this.bpmConfNode = bpmConfNode;
		this.type = type;
		this.receiver = receiver;
		this.dueDate = dueDate;
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
	@JoinColumn(name = "TEMPLATE_ID")
	public BpmMailTemplate getBpmMailTemplate() {
		return this.bpmMailTemplate;
	}

	/**
	 * @param bpmMailTemplate
	 *            null.
	 */
	public void setBpmMailTemplate(BpmMailTemplate bpmMailTemplate) {
		this.bpmMailTemplate = bpmMailTemplate;
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
	@Column(name = "RECEIVER", length = 200)
	public String getReceiver() {
		return this.receiver;
	}

	/**
	 * @param receiver
	 *            null.
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/** @return null. */
	@Column(name = "DUE_DATE", length = 50)
	public String getDueDate() {
		return this.dueDate;
	}

	/**
	 * @param dueDate
	 *            null.
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
}

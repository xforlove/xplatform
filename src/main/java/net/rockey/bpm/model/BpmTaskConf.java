package net.rockey.bpm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BPM_TASK_CONF")
public class BpmTaskConf implements Serializable {

	/** null. */
	private Long id;

	/** null. */
	private String businessKey;

	/** null. */
	private String taskDefinitionKey;

	/** null. */
	private String assignee;

	public BpmTaskConf() {
	}

	public BpmTaskConf(String businessKey, String taskDefinitionKey,
			String assignee) {
		this.businessKey = businessKey;
		this.taskDefinitionKey = taskDefinitionKey;
		this.assignee = assignee;
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
	@Column(name = "BUSINESS_KEY", length = 200)
	public String getBusinessKey() {
		return this.businessKey;
	}

	/**
	 * @param businessKey
	 *            null.
	 */
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	/** @return null. */
	@Column(name = "TASK_DEFINITION_KEY", length = 200)
	public String getTaskDefinitionKey() {
		return this.taskDefinitionKey;
	}

	/**
	 * @param taskDefinitionKey
	 *            null.
	 */
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	/** @return null. */
	@Column(name = "ASSIGNEE", length = 200)
	public String getAssignee() {
		return this.assignee;
	}

	/**
	 * @param assignee
	 *            null.
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
}

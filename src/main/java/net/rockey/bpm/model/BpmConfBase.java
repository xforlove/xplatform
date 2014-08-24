package net.rockey.bpm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bpm_conf_base")
public class BpmConfBase implements Serializable {

	/** null. */
	private Long id;

	/** null. */
	private String processDefinitionId;

	/** null. */
	private String processDefinitionKey;

	/** null. */
	private Integer processDefinitionVersion;

	/** . */
	private Set<BpmConfNode> bpmConfNodes = new HashSet<BpmConfNode>(0);

	/** . */
	private Set<BpmProcess> bpmProcesses = new HashSet<BpmProcess>(0);

	public BpmConfBase() {
	}

	public BpmConfBase(String processDefinitionId, String processDefinitionKey,
			Integer processDefinitionVersion, Set<BpmConfNode> bpmConfNodes,
			Set<BpmProcess> bpmProcesses) {
		this.processDefinitionId = processDefinitionId;
		this.processDefinitionKey = processDefinitionKey;
		this.processDefinitionVersion = processDefinitionVersion;
		this.bpmConfNodes = bpmConfNodes;
		this.bpmProcesses = bpmProcesses;
	}

	/** @return null. */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
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
	@Column(name = "process_definition_id", length = 200)
	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	/**
	 * @param processDefinitionId
	 *            null.
	 */
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	/** @return null. */
	@Column(name = "process_definition_key", length = 200)
	public String getProcessDefinitionKey() {
		return this.processDefinitionKey;
	}

	/**
	 * @param processDefinitionKey
	 *            null.
	 */
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	/** @return null. */
	@Column(name = "process_definition_version")
	public Integer getProcessDefinitionVersion() {
		return this.processDefinitionVersion;
	}

	/**
	 * @param processDefinitionVersion
	 *            null.
	 */
	public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfBase")
	public Set<BpmConfNode> getBpmConfNodes() {
		return this.bpmConfNodes;
	}

	/**
	 * @param bpmConfNodes
	 *            .
	 */
	public void setBpmConfNodes(Set<BpmConfNode> bpmConfNodes) {
		this.bpmConfNodes = bpmConfNodes;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfBase")
	public Set<BpmProcess> getBpmProcesses() {
		return this.bpmProcesses;
	}

	/**
	 * @param bpmProcesses
	 *            .
	 */
	public void setBpmProcesses(Set<BpmProcess> bpmProcesses) {
		this.bpmProcesses = bpmProcesses;
	}
}

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "bpm_category")
public class BpmCategory implements Serializable {

	/** null. */
	private Long id;

	/** null. */
	private String name;

	/** null. */
	private Integer priority;

	/** . */
	private Set<BpmProcess> bpmProcesses = new HashSet<BpmProcess>(0);

	public BpmCategory() {
		// default.
	}

	public BpmCategory(String name, Integer priority,
			Set<BpmProcess> bpmProcesses) {
		this.name = name;
		this.priority = priority;
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
	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            null.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** @return null. */
	@Column(name = "priority")
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * @param priority
	 *            null.
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmCategory")
	@OrderBy("priority")
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

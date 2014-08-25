package net.rockey.bpm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bpm_process")
public class BpmProcess implements Serializable {

	/** null. */
	private Long id;

	/** null. */
	private BpmConfBase bpmConfBase;

	/** null. */
	private BpmCategory bpmCategory;

	/** null. */
	private String name;

	/** null. */
	private Integer priority;

	/** null. */
	private String descn;

	/** null. */
	private Integer needTaskConf;

	/** null. */
	private String code;

	/** . */
	private Set<BpmTaskDef> bpmTaskDefs = new HashSet<BpmTaskDef>(0);

	/** . */
	private Set<BpmTaskDefNotice> bpmTaskDefNotices = new HashSet<BpmTaskDefNotice>(
			0);

	public BpmProcess() {
	}

	public BpmProcess(BpmConfBase bpmConfBase, BpmCategory bpmCategory,
			String name, Integer priority, String descn, Integer needTaskConf,
			String code, Set<BpmTaskDef> bpmTaskDefs,
			Set<BpmTaskDefNotice> bpmTaskDefNotices) {
		this.bpmConfBase = bpmConfBase;
		this.bpmCategory = bpmCategory;
		this.name = name;
		this.priority = priority;
		this.descn = descn;
		this.needTaskConf = needTaskConf;
		this.code = code;
		this.bpmTaskDefs = bpmTaskDefs;
		this.bpmTaskDefNotices = bpmTaskDefNotices;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conf_base_id")
	public BpmConfBase getBpmConfBase() {
		return this.bpmConfBase;
	}

	/**
	 * @param bpmConfBase
	 *            null.
	 */
	public void setBpmConfBase(BpmConfBase bpmConfBase) {
		this.bpmConfBase = bpmConfBase;
	}

	/** @return null. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public BpmCategory getBpmCategory() {
		return this.bpmCategory;
	}

	/**
	 * @param bpmCategory
	 *            null.
	 */
	public void setBpmCategory(BpmCategory bpmCategory) {
		this.bpmCategory = bpmCategory;
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

	/** @return null. */
	@Column(name = "descn", length = 200)
	public String getDescn() {
		return this.descn;
	}

	/**
	 * @param descn
	 *            null.
	 */
	public void setDescn(String descn) {
		this.descn = descn;
	}

	/** @return null. */
	@Column(name = "need_task_conf")
	public Integer getNeedTaskConf() {
		return this.needTaskConf;
	}

	public void setNeedTaskConf(Integer needTaskConf) {
		this.needTaskConf = needTaskConf;
	}

	/** @return null. */
	@Column(name = "code", length = 64)
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 *            null.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmProcess")
	public Set<BpmTaskDef> getBpmTaskDefs() {
		return this.bpmTaskDefs;
	}

	/**
	 * @param bpmTaskDefs
	 *            .
	 */
	public void setBpmTaskDefs(Set<BpmTaskDef> bpmTaskDefs) {
		this.bpmTaskDefs = bpmTaskDefs;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmProcess")
	public Set<BpmTaskDefNotice> getBpmTaskDefNotices() {
		return this.bpmTaskDefNotices;
	}

	/**
	 * @param bpmTaskDefNotices
	 *            .
	 */
	public void setBpmTaskDefNotices(Set<BpmTaskDefNotice> bpmTaskDefNotices) {
		this.bpmTaskDefNotices = bpmTaskDefNotices;
	}

}

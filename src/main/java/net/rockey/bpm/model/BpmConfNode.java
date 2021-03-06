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
@Table(name = "bpm_conf_node")
public class BpmConfNode implements Serializable {

	/** null. */
	private Long id;

	/** null. */
	private BpmConfBase bpmConfBase;

	/** null. */
	private String code;

	/** null. */
	private String name;

	/** null. */
	private String type;

	/** null. */
	private Integer confUser;

	/** null. */
	private Integer confListener;

	/** null. */
	private Integer confRule;

	/** null. */
	private Integer confForm;

	/** null. */
	private Integer confOperation;

	/** null. */
	private Integer confNotice;

	/** null. */
	private Integer priority;

	/** . */
	private Set<BpmConfListener> bpmConfListeners = new HashSet<BpmConfListener>(
			0);

	/** . */
	private Set<BpmConfNotice> bpmConfNotices = new HashSet<BpmConfNotice>(0);

	/** . */
	private Set<BpmConfUser> bpmConfUsers = new HashSet<BpmConfUser>(0);

	/** . */
	private Set<BpmConfCountersign> bpmConfCountersigns = new HashSet<BpmConfCountersign>(
			0);

	/** . */
	private Set<BpmConfForm> bpmConfForms = new HashSet<BpmConfForm>(0);

	/** . */
	private Set<BpmConfRule> bpmConfRules = new HashSet<BpmConfRule>(0);

	/** . */
	private Set<BpmConfOperation> bpmConfOperations = new HashSet<BpmConfOperation>(
			0);

	public BpmConfNode() {
	}

	public BpmConfNode(BpmConfBase bpmConfBase, String code, String name,
			String type, Integer confUser, Integer confListener,
			Integer confRule, Integer confForm, Integer confOperation,
			Integer confNotice, Integer priority,
			Set<BpmConfListener> bpmConfListeners,
			Set<BpmConfNotice> bpmConfNotices, Set<BpmConfUser> bpmConfUsers,
			Set<BpmConfCountersign> bpmConfCountersigns,
			Set<BpmConfForm> bpmConfForms, Set<BpmConfRule> bpmConfRules,
			Set<BpmConfOperation> bpmConfOperations) {
		this.bpmConfBase = bpmConfBase;
		this.code = code;
		this.name = name;
		this.type = type;
		this.confUser = confUser;
		this.confListener = confListener;
		this.confRule = confRule;
		this.confForm = confForm;
		this.confOperation = confOperation;
		this.confNotice = confNotice;
		this.priority = priority;
		this.bpmConfListeners = bpmConfListeners;
		this.bpmConfNotices = bpmConfNotices;
		this.bpmConfUsers = bpmConfUsers;
		this.bpmConfCountersigns = bpmConfCountersigns;
		this.bpmConfForms = bpmConfForms;
		this.bpmConfRules = bpmConfRules;
		this.bpmConfOperations = bpmConfOperations;
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
	@Column(name = "code", length = 200)
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
	@Column(name = "type", length = 200)
	public String getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            null.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** @return null. */
	@Column(name = "conf_user")
	public Integer getConfUser() {
		return this.confUser;
	}

	/**
	 * @param confUser
	 *            null.
	 */
	public void setConfUser(Integer confUser) {
		this.confUser = confUser;
	}

	/** @return null. */
	@Column(name = "conf_listener")
	public Integer getConfListener() {
		return this.confListener;
	}

	/**
	 * @param confListener
	 *            null.
	 */
	public void setConfListener(Integer confListener) {
		this.confListener = confListener;
	}

	/** @return null. */
	@Column(name = "conf_rule")
	public Integer getConfRule() {
		return this.confRule;
	}

	/**
	 * @param confRule
	 *            null.
	 */
	public void setConfRule(Integer confRule) {
		this.confRule = confRule;
	}

	/** @return null. */
	@Column(name = "conf_form")
	public Integer getConfForm() {
		return this.confForm;
	}

	/**
	 * @param confForm
	 *            null.
	 */
	public void setConfForm(Integer confForm) {
		this.confForm = confForm;
	}

	/** @return null. */
	@Column(name = "conf_operation")
	public Integer getConfOperation() {
		return this.confOperation;
	}

	/**
	 * @param confOperation
	 *            null.
	 */
	public void setConfOperation(Integer confOperation) {
		this.confOperation = confOperation;
	}

	/** @return null. */
	@Column(name = "conf_notice")
	public Integer getConfNotice() {
		return this.confNotice;
	}

	/**
	 * @param confNotice
	 *            null.
	 */
	public void setConfNotice(Integer confNotice) {
		this.confNotice = confNotice;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfNode")
	public Set<BpmConfListener> getBpmConfListeners() {
		return this.bpmConfListeners;
	}

	/**
	 * @param bpmConfListeners
	 *            .
	 */
	public void setBpmConfListeners(Set<BpmConfListener> bpmConfListeners) {
		this.bpmConfListeners = bpmConfListeners;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfNode")
	public Set<BpmConfNotice> getBpmConfNotices() {
		return this.bpmConfNotices;
	}

	/**
	 * @param bpmConfNotices
	 *            .
	 */
	public void setBpmConfNotices(Set<BpmConfNotice> bpmConfNotices) {
		this.bpmConfNotices = bpmConfNotices;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfNode")
	public Set<BpmConfUser> getBpmConfUsers() {
		return this.bpmConfUsers;
	}

	/**
	 * @param bpmConfUsers
	 *            .
	 */
	public void setBpmConfUsers(Set<BpmConfUser> bpmConfUsers) {
		this.bpmConfUsers = bpmConfUsers;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfNode")
	public Set<BpmConfCountersign> getBpmConfCountersigns() {
		return this.bpmConfCountersigns;
	}

	/**
	 * @param bpmConfCountersigns
	 *            .
	 */
	public void setBpmConfCountersigns(
			Set<BpmConfCountersign> bpmConfCountersigns) {
		this.bpmConfCountersigns = bpmConfCountersigns;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfNode")
	public Set<BpmConfForm> getBpmConfForms() {
		return this.bpmConfForms;
	}

	/**
	 * @param bpmConfForms
	 *            .
	 */
	public void setBpmConfForms(Set<BpmConfForm> bpmConfForms) {
		this.bpmConfForms = bpmConfForms;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfNode")
	public Set<BpmConfRule> getBpmConfRules() {
		return this.bpmConfRules;
	}

	/**
	 * @param bpmConfRules
	 *            .
	 */
	public void setBpmConfRules(Set<BpmConfRule> bpmConfRules) {
		this.bpmConfRules = bpmConfRules;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmConfNode")
	public Set<BpmConfOperation> getBpmConfOperations() {
		return this.bpmConfOperations;
	}

	/**
	 * @param bpmConfOperations
	 *            .
	 */
	public void setBpmConfOperations(Set<BpmConfOperation> bpmConfOperations) {
		this.bpmConfOperations = bpmConfOperations;
	}
}

package net.rockey.bpm.model;

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
@Table(name = "BPM_MAIL_TEMPLATE")
public class BpmMailTemplate implements java.io.Serializable {

	private static final long serialVersionUID = 0L;

	/** null. */
	private Long id;

	/** null. */
	private String name;

	/** null. */
	private String subject;

	/** null. */
	private String content;

	/** . */
	private Set<BpmTaskDefNotice> bpmTaskDefNotices = new HashSet<BpmTaskDefNotice>(
			0);

	/** . */
	private Set<BpmConfNotice> bpmConfNotices = new HashSet<BpmConfNotice>(0);

	public BpmMailTemplate() {
	}

	public BpmMailTemplate(String name, String subject, String content,
			Set<BpmTaskDefNotice> bpmTaskDefNotices,
			Set<BpmConfNotice> bpmConfNotices) {
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.bpmTaskDefNotices = bpmTaskDefNotices;
		this.bpmConfNotices = bpmConfNotices;
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
	@Column(name = "NAME", length = 50)
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
	@Column(name = "SUBJECT", length = 100)
	public String getSubject() {
		return this.subject;
	}

	/**
	 * @param subject
	 *            null.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/** @return null. */
	@Column(name = "CONTENT", length = 200)
	public String getContent() {
		return this.content;
	}

	/**
	 * @param content
	 *            null.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmMailTemplate")
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

	/** @return . */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bpmMailTemplate")
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
}
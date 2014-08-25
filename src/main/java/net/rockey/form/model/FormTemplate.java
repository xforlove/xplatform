package net.rockey.form.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FORM_TEMPLATE")
public class FormTemplate implements java.io.Serializable {
	private static final long serialVersionUID = 0L;

	/** null. */
	private Long id;

	/** null. */
	private Integer type;

	/** null. */
	private String name;

	/** null. */
	private String content;

	public FormTemplate() {
	}

	public FormTemplate(Integer type, String name, String content) {
		this.type = type;
		this.name = name;
		this.content = content;
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
	@Column(name = "NAME", length = 200)
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
	@Column(name = "CONTENT", length = 2000)
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
}

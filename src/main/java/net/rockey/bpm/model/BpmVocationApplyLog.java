package net.rockey.bpm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bpm_vocation_apply_log")
public class BpmVocationApplyLog implements java.io.Serializable {

	private Long id;
	private String name;
	private String type;
	private double days;
	private String descn;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 64)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "days")
	public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}

	@Column(name = "descn", length = 64)
	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

}

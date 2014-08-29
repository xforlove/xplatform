package net.rockey.bpm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bpm_vocation_apply_log")
public class BpmVocationApplyLog implements java.io.Serializable {

	private Long id;
	private Long creator;
	private String applyTime;
	private String type;
	private String beginDate;
	private String beginTime;
	private String endDate;
	private String endTime;
	private double duration;
	private String statFlag;
	private String descn;

	private List<BpmVocationProcDtl> procDtls = new ArrayList<BpmVocationProcDtl>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "apply_time", length = 14, nullable = false)
	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "type", length = 3, nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "begin_date", length = 8)
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "begin_time", length = 6)
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_date", length = 8)
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "end_time", length = 6)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "duration", nullable = false)
	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	@Column(name = "descn", length = 255)
	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	@OneToMany(mappedBy = "applyLog", cascade = CascadeType.ALL)
	public List<BpmVocationProcDtl> getProcDtls() {
		return procDtls;
	}

	public void setProcDtls(List<BpmVocationProcDtl> procDtls) {
		this.procDtls = procDtls;
	}

	@Column(name = "creator")
	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Column(name = "stat_flag", nullable = false, length = 20)
	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

}

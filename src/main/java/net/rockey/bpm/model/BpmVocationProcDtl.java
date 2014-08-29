package net.rockey.bpm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bpm_vocation_proc_dtl")
public class BpmVocationProcDtl implements java.io.Serializable {

	private Long id;
	private BpmVocationApplyLog applyLog;
	private String procDate;
	private String procTime;
	private Long userId;
	private String action;
	private String descn;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "apply_seq_id")
	public BpmVocationApplyLog getApplyLog() {
		return applyLog;
	}

	public void setApplyLog(BpmVocationApplyLog applyLog) {
		this.applyLog = applyLog;
	}

	@Column(name = "proc_date", length = 8)
	public String getProcDate() {
		return procDate;
	}

	public void setProcDate(String procDate) {
		this.procDate = procDate;
	}

	@Column(name = "proc_time", length = 6)
	public String getProcTime() {
		return procTime;
	}

	public void setProcTime(String procTime) {
		this.procTime = procTime;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "descn", length = 255)
	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	@Column(name = "action", length = 100)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

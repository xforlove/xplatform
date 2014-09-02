package net.rockey.bpm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bpm_leave_apply_log")
public class BpmLeaveApplyLog implements java.io.Serializable {

	private Long id;

	/** 请假类型，如：年假、事假、婚假、病假、调休等 */
	private String type;

	/** 计划开始时间 */
	private String applyBeginTime;

	/** 计划结束时间 */
	private String applyEndTime;

	/** 计划请假天数 */
	private double applyDuration;

	/** 实际开始日期 */
	private String actualBeginTime;

	/** 实际结束日期 */
	private String actualEndTime;

	/** 实际请假天数 */
	private double actualDuration;

	/** 申请单状态 */
	private String statFlag;

	/** 请假原因 */
	private String reason;

	/** 申请人员ID */
	private Long creator;
	private String createTime;

	/** 审核人员ID */
	private Long auditor;
	private String auditTime;
	
	/** 流程相关属性 */
	private String processInstanceId;
	private String processStatFlag;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "type", length = 32)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "applyl_begin_time", length = 32)
	public String getApplyBeginTime() {
		return applyBeginTime;
	}

	public void setApplyBeginTime(String applyBeginTime) {
		this.applyBeginTime = applyBeginTime;
	}

	@Column(name = "apply_end_time", length = 32)
	public String getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	@Column(name = "apply_duration")
	public double getApplyDuration() {
		return applyDuration;
	}

	public void setApplyDuration(double applyDuration) {
		this.applyDuration = applyDuration;
	}

	@Column(name = "actual_begin_time", length = 32)
	public String getActualBeginTime() {
		return actualBeginTime;
	}

	public void setActualBeginTime(String actualBeginTime) {
		this.actualBeginTime = actualBeginTime;
	}

	@Column(name = "actual_end_time", length = 32)
	public String getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(String actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	@Column(name = "actual_duration")
	public double getActualDuration() {
		return actualDuration;
	}

	public void setActualDuration(double actualDuration) {
		this.actualDuration = actualDuration;
	}

	@Column(name = "stat_flag", length = 32)
	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

	@Column(name = "reason", length = 256)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "creator")
	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Column(name = "creator_time", length = 32)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "auditor")
	public Long getAuditor() {
		return auditor;
	}

	public void setAuditor(Long auditor) {
		this.auditor = auditor;
	}

	@Column(name = "audit_time", length = 32)
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "process_instance_id", length = 64)
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	
	@Column(name = "process_stat_flag", length = 64)
	public String getProcessStatFlag() {
		return processStatFlag;
	}

	public void setProcessStatFlag(String processStatFlag) {
		this.processStatFlag = processStatFlag;
	}
	
	

}

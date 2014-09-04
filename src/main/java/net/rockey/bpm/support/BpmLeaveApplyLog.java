package net.rockey.bpm.support;

public class BpmLeaveApplyLog {

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

	/** 上级审核结果 */
	private int leaderAuditPass;

	/** 驳回理由 */
	private String rejectReason;

	/** 申请人员ID */
	private Long creator;
	private String creatorName;
	private String createTime;

	/** 审核人员ID */
	private Long auditor;
	private String auditorName;
	private String auditTime;

	/** 记录人员ID */
	private Long recorder;
	private String recorderName;
	private String recordTime;

	public int getLeaderAuditPass() {
		return leaderAuditPass;
	}

	public void setLeaderAuditPass(int leaderAuditPass) {
		this.leaderAuditPass = leaderAuditPass;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApplyBeginTime() {
		return applyBeginTime;
	}

	public void setApplyBeginTime(String applyBeginTime) {
		this.applyBeginTime = applyBeginTime;
	}

	public String getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public double getApplyDuration() {
		return applyDuration;
	}

	public void setApplyDuration(double applyDuration) {
		this.applyDuration = applyDuration;
	}

	public String getActualBeginTime() {
		return actualBeginTime;
	}

	public void setActualBeginTime(String actualBeginTime) {
		this.actualBeginTime = actualBeginTime;
	}

	public String getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(String actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public double getActualDuration() {
		return actualDuration;
	}

	public void setActualDuration(double actualDuration) {
		this.actualDuration = actualDuration;
	}

	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getAuditor() {
		return auditor;
	}

	public void setAuditor(Long auditor) {
		this.auditor = auditor;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public Long getRecorder() {
		return recorder;
	}

	public void setRecorder(Long recorder) {
		this.recorder = recorder;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getRecorderName() {
		return recorderName;
	}

	public void setRecorderName(String recorderName) {
		this.recorderName = recorderName;
	}

}

package net.rockey.core.util;

public interface CONSTANTS {

	public static final String USER_PASSWORD_DEFAULT = "000000";

	public static final String STAT_FLAG_NORMARL = "NORMAL";

	public static final String STAT_FLAG_CLOSE = "CLOSE";

	public static final String USER_EMAIL_DEFAULT = "test@163.com";

	public static final String USER_PHONE_DEFAULT = "13888888888";

	/** 流程业务类型：请假申请 */
	public static final String BPM_BUSINESS_TYPE_VOCATION_REQUEST = "vocationRequest";

	/** 流程业务类型：权限申请 */
	public static final String BPM_BUSINESS_TYPE_PERMISSION_REQUEST = "permissionRequest";

	/** 业务申请单通用状态：I-初始化(待审核) */
	public static final String BUSINESS_APPLY_LOG_STAT_FLAG_INIT = "I";

	/** 业务申请单通用状态：U-待上传资料 */
	public static final String BUSINESS_APPLY_LOG_STAT_FLAG_UPLOAD = "U";

	/** 业务申请单通用状态：N-正常(审核通过) */
	public static final String BUSINESS_APPLY_LOG_STAT_FLAG_NORMAL = "N";

	/** 业务申请单通用状态：R-审核拒绝 */
	public static final String BUSINESS_APPLY_LOG_STAT_FLAG_REFUSE = "R";

	/** 业务申请单通用状态：C-关闭(取消) */
	public static final String BUSINESS_APPLY_LOG_STAT_FLAG_CLOSE = "C";

	public static final String OPERATION_BPM_DEFINITION_ID = "processDefinitionId";
	public static final String OPERATION_BPM_PROCESS_ID = "bpmProcessId";
	public static final String OPERATION_TASK_ID = "taskId";
	public static final String OPERATION_BUSINESS_TYPE = "businessType";
	public static final String OPERATION_BUSINESS_KEY = "businessKey";

	public static final String OPERATION_COMMENT_COMPLETE = "完成";
	public static final String OPERATION_COMMENT_AGREE = "同意";
	public static final String OPERATION_COMMENT_REFUSE = "拒绝";

	public static final String PROCESS_STATUS_DRAFT = "DRAFT_PROCESS";
	public static final String PROCESS_STATUS_RUNNING = "RUNNING";

}

package net.rockey.core.util;

public interface CONSTANTS {

	/** 属性默认值 */
	public static final String DEFAULT_VALUE_PASSWORD = "000000";
	public static final String DEFAULT_VALUE_EMAIL = "test@qq.com";
	public static final String DEFAULT_VALUE_CELLPHONE = "13888888888";

	/** 状态标志 */
	public static final String STAT_FLAG_NORMARL = "N";
	public static final String STAT_FLAG_CLOSE = "C";

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

	public static final String PROCESS_PARAMETER_BPM_DEFINITION_ID = "processDefinitionId";
	public static final String PROCESS_PARAMETER_BPM_PROCESS_ID = "bpmProcessId";
	public static final String PROCESS_PARAMETER_TASK_ID = "taskId";
	public static final String PROCESS_PARAMETER_BUSINESS_TYPE = "businessType";
	public static final String PROCESS_PARAMETER_BUSINESS_KEY = "businessKey";

	public static final String OPERATION_COMMENT_COMPLETE = "完成";
	public static final String OPERATION_COMMENT_AGREE = "同意";
	public static final String OPERATION_COMMENT_REFUSE = "拒绝";

	public static final String PROCESS_STATUS_DRAFT = "DRAFT_PROCESS";
	public static final String PROCESS_STATUS_RUNNING = "RUNNING";

}

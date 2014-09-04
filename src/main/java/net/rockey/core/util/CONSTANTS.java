package net.rockey.core.util;

public interface CONSTANTS {

	/** 属性默认值 */
	public static final String DEFAULT_VALUE_PASSWORD = "000000";
	public static final String DEFAULT_VALUE_EMAIL = "test@qq.com";
	public static final String DEFAULT_VALUE_CELLPHONE = "13888888888";

	/** 状态标志 */
	public static final String STAT_FLAG_NORMARL = "N";
	public static final String STAT_FLAG_CLOSE = "C";

	/** 流程参数：流程定义ID */
	public static final String PROCESS_PARAMETER_BPM_DEFINITION_ID = "processDefinitionId";
	
	/** 流程参数：流程ID */
	public static final String PROCESS_PARAMETER_BPM_PROCESS_ID = "bpmProcessId";
	
	/** 流程参数：任务ID */
	public static final String PROCESS_PARAMETER_TASK_ID = "taskId";
	
	/** 流程参数：Busniess Key. */
	public static final String PROCESS_PARAMETER_BUSINESS_KEY = "businessKey";

	/** 操作说明：完成 */
	public static final String OPERATION_COMMENT_COMPLETE = "完成";

	/** 流程状态：流程草稿 */
	public static final String PROCESS_STATUS_DRAFT_PROCESS = "DRAFT_PROCESS";
	
	/** 流程状态：任务草稿 */
	public static final String PROCESS_STATUS_DRAFT_TASK = "DRAFT_TASK";
	
	/** 流程状态：运行中 */
	public static final String PROCESS_STATUS_RUNNING = "RUNNING";

}

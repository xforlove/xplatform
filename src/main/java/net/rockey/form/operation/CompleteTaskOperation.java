package net.rockey.form.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.rockey.bpm.cmd.CompleteTaskWithCommentCmd;
import net.rockey.bpm.manager.BpmLeaveApplyLogManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmLeaveApplyLog;
import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.ShiroUtils;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;

public class CompleteTaskOperation extends AbstractOperation<Void> {

	private final Logger log = LogUtils.getLogger(CompleteTaskOperation.class,
			true);

	@Override
	public Void execute(CommandContext commandContext) {
		ProcessEngine processEngine = getProcessEngine();
		String taskId = getParamValue(CONSTANTS.OPERATION_TASK_ID);
		String businessType = getParamValue(CONSTANTS.OPERATION_BUSINESS_TYPE);

		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		/* 处理抄送任务 */
		// if ("copy".equals(task.getCategory())) {
		// new DeleteTaskWithCommentCmd(taskId, "已阅").execute(commandContext);
		//
		// return null;
		// }

		// 先保存草稿
		new SaveDraftOperation().execute(getParameters());

		// 先设置登录用户
		IdentityService identityService = processEngine.getIdentityService();
		identityService.setAuthenticatedUserId((String) ShiroUtils
				.getAttribute("user_id"));

		if (task == null) {
			throw new IllegalStateException("任务不存在");
		}

		log.info(task.getDelegationState());

		/* 处理委办任务 */
		// if (DelegationState.PENDING == task.getDelegationState()) {
		// taskService.resolveTask(taskId);
		//
		// return null;
		// }

		/* 处理子任务 */
		// if ("subtask".equals(task.getCategory())) {
		// new DeleteTaskWithCommentCmd(taskId, "完成").execute(commandContext);
		//
		// int count = getJdbcTemplate().queryForObject(
		// "select count(*) from ACT_RU_TASK where PARENT_TASK_ID_=?",
		// Integer.class, task.getParentTaskId());
		//
		// if (count > 1) {
		// return null;
		// }
		//
		// taskId = task.getParentTaskId();
		// }

		String processInstanceId = task.getProcessInstanceId();

		// 通过流程实例号获得业务对象
		BpmLeaveApplyLog applyLog = this.getBpmLeaveApplyLogManager()
				.findUniqueBy("processInstanceId", processInstanceId);

		Map<String, Object> processParameters = new HashMap<String, Object>();

		// 构建流程参数
		Map<String, Object> parameters = this.getParameters();
		for (Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();

			/*
			 * 参数结构：pp_reason 前缀 pp_ 的意思是 process parameter, 后面是属性名称
			 */
			if (key.startsWith("pp_")) {
				String[] params = key.split("_");
				processParameters.put(params[1], entry.getValue());
			}

		}

		new CompleteTaskWithCommentCmd(taskId, processParameters,
				CONSTANTS.OPERATION_COMMENT_COMPLETE).execute(commandContext);

		log.info("业务类型 := " + businessType);

		applyLog.setStatFlag(CONSTANTS.BUSINESS_APPLY_LOG_STAT_FLAG_NORMAL);
		applyLog.setProcessStatFlag(CONSTANTS.PROCESS_STATUS_RUNNING);

		this.getBpmLeaveApplyLogManager().save(applyLog);

		return null;
	}

	public BpmProcessManager getBpmProcessManager() {
		return ApplicationContextHelper.getBean(BpmProcessManager.class);
	}

	public BpmLeaveApplyLogManager getBpmLeaveApplyLogManager() {
		return ApplicationContextHelper.getBean(BpmLeaveApplyLogManager.class);
	}

}

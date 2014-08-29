package net.rockey.form.operation;

import java.util.HashMap;
import java.util.Map;

import net.rockey.bpm.FormInfo;
import net.rockey.bpm.cmd.CompleteTaskWithCommentCmd;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.manager.BpmVocationApplyLogManager;
import net.rockey.bpm.model.BpmVocationApplyLog;
import net.rockey.bpm.model.BpmVocationProcDtl;
import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.CPublic;
import net.rockey.core.util.LogUtils;
import net.rockey.form.manager.RecordManager;
import net.rockey.form.model.Prop;
import net.rockey.form.model.Record;
import net.rockey.form.support.RecordBuilder;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;

public class CompleteTaskOperation extends AbstractOperation<Void> {

	private final Logger log = LogUtils.getLogger(CompleteTaskOperation.class,
			true);

	@Override
	public Void execute(CommandContext commandContext) {
		ProcessEngine processEngine = getProcessEngine();
		String taskId = getParamValue(CONSTANTS.OPERATION_TASK_ID);
		String businessType = getParamValue(CONSTANTS.OPERATION_BUSINESS_TYPE);
		Long userId = this.getCurrentUserId();

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
		identityService.setAuthenticatedUserId(String.valueOf(SecurityUtils
				.getSubject().getSession().getAttribute("user_id")));

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
		Record record = getRecordManager().findByRef(processInstanceId);

		Map<String, Object> processParameters = new HashMap<String, Object>();

		if (record == null) {
			new CompleteTaskWithCommentCmd(taskId, processParameters,
					CONSTANTS.OPERATION_COMMENT_COMPLETE)
					.execute(commandContext);

			return null;
		}

		// 如果有表单，就从数据库获取数据
		for (Prop prop : record.getProps()) {
			String key = prop.getCode();
			String value = prop.getValue();

			processParameters.put(key, value);
		}

		new CompleteTaskWithCommentCmd(taskId, processParameters,
				CONSTANTS.OPERATION_COMMENT_COMPLETE).execute(commandContext);
		record = new RecordBuilder().build(record,
				CONSTANTS.PROCESS_STATUS_RUNNING, processInstanceId);
		this.getRecordManager().save(record);

		log.info("业务类型 := " + businessType);

		if (CONSTANTS.BPM_BUSINESS_TYPE_VOCATION_REQUEST.equals(businessType)) {

			BpmVocationApplyLog applyLog = this.getBpmVocationApplyLogManager()
					.get(record.getApplySeqId());

			applyLog.setStatFlag(CONSTANTS.BUSINESS_APPLY_LOG_STAT_FLAG_NORMAL);

			BpmVocationProcDtl procDtl = new BpmVocationProcDtl();
			procDtl.setApplyLog(applyLog);
			procDtl.setProcDate(CPublic.getDate());
			procDtl.setProcTime(CPublic.getTime());
			procDtl.setUserId(userId);

			applyLog.getProcDtls().add(procDtl);

			this.getBpmVocationApplyLogManager().save(applyLog);

		} else {
			log.error("businessType is unknown.");
		}

		return null;
	}

	public BpmProcessManager getBpmProcessManager() {
		return ApplicationContextHelper.getBean(BpmProcessManager.class);
	}

	public RecordManager getRecordManager() {
		return ApplicationContextHelper.getBean(RecordManager.class);
	}

	public BpmVocationApplyLogManager getBpmVocationApplyLogManager() {
		return ApplicationContextHelper
				.getBean(BpmVocationApplyLogManager.class);
	}

}

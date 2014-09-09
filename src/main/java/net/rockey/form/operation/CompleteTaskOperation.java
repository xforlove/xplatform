package net.rockey.form.operation;

import java.util.HashMap;
import java.util.Map;

import net.rockey.bpm.cmd.CompleteTaskWithCommentCmd;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.ShiroUtils;
import net.rockey.form.keyvalue.KeyValue;
import net.rockey.form.keyvalue.Record;
import net.rockey.form.support.RecordBuilder;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompleteTaskOperation extends AbstractOperation<Void> {

	private static final Logger log = LoggerFactory
			.getLogger(CompleteTaskOperation.class);

	@Override
	public Void execute(CommandContext commandContext) {
		ProcessEngine processEngine = getProcessEngine();
		String taskId = getParamValue(CONSTANTS.PROCESS_PARAMETER_TASK_ID);
		KeyValue keyValue = getKeyValue();

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

		log.info("{}", task.getDelegationState());

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

		Map<String, Object> processParameters = new HashMap<String, Object>();

		// 通过流程实例号获得业务对象
		Record record = keyValue.findByRef(processInstanceId);

		if (record == null) {
			new CompleteTaskWithCommentCmd(taskId, processParameters,
					CONSTANTS.OPERATION_COMMENT_COMPLETE)
					.execute(commandContext);

			return null;
		}

		// 构建流程参数
		processParameters = this.getProcessParameters();
		
		log.info("processParameters : {}", processParameters);

		new CompleteTaskWithCommentCmd(taskId, processParameters,
				CONSTANTS.OPERATION_COMMENT_COMPLETE).execute(commandContext);

		record = new RecordBuilder().build(record,
				CONSTANTS.PROCESS_STATUS_RUNNING, processParameters);

		keyValue.save(record);

		return null;
	}

	public BpmProcessManager getBpmProcessManager() {
		return ApplicationContextHelper.getBean(BpmProcessManager.class);
	}

	public KeyValue getKeyValue() {
		return ApplicationContextHelper.getBean(KeyValue.class);
	}

}

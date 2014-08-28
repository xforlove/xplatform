package net.rockey.form.operation;

import java.util.HashMap;
import java.util.Map;

import net.rockey.bpm.FormInfo;
import net.rockey.bpm.cmd.CompleteTaskWithCommentCmd;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.core.spring.ApplicationContextHelper;
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

	public static final String OPERATION_TASK_ID = "taskId";
	public static final String OPERATION_COMMENT = "完成";
	public static final String STATUS_RUNNING = "RUNNING";

	@Override
	public Void execute(CommandContext commandContext) {
		ProcessEngine processEngine = getProcessEngine();
		String taskId = getParamValue(OPERATION_TASK_ID);

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
		identityService.setAuthenticatedUserId((String) SecurityUtils
				.getSubject().getSession().getAttribute("user_id"));

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

		FormService formService = processEngine.getFormService();
		String taskFormKey = formService.getTaskFormKey(
				task.getProcessDefinitionId(), task.getTaskDefinitionKey());
		FormInfo formInfo = new FormInfo();
		formInfo.setTaskId(taskId);
		formInfo.setFormKey(taskFormKey);

		// 尝试根据表单里字段的类型，进行转换
		Map<String, String> formTypeMap = new HashMap<String, String>();

		String processInstanceId = task.getProcessInstanceId();
		Record record = getRecordManager().findByRef(processInstanceId);

		Map<String, Object> processParameters = new HashMap<String, Object>();

		if (record == null) {
			new CompleteTaskWithCommentCmd(taskId, processParameters,
					OPERATION_COMMENT).execute(commandContext);

			return null;
		}

		// 如果有表单，就从数据库获取数据
		for (Prop prop : record.getProps()) {
			String key = prop.getCode();
			String value = prop.getValue();

			processParameters.put(key, value);
		}

		new CompleteTaskWithCommentCmd(taskId, processParameters,
				OPERATION_COMMENT).execute(commandContext);
		record = new RecordBuilder().build(record, STATUS_RUNNING,
				processInstanceId);
		this.getRecordManager().save(record);

		return null;
	}

	public BpmProcessManager getBpmProcessManager() {
		return ApplicationContextHelper.getBean(BpmProcessManager.class);
	}

	public RecordManager getRecordManager() {
		return ApplicationContextHelper.getBean(RecordManager.class);
	}

}

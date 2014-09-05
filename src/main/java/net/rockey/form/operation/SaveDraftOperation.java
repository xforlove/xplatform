package net.rockey.form.operation;

import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.ShiroUtils;
import net.rockey.form.keyvalue.KeyValue;
import net.rockey.form.keyvalue.Record;
import net.rockey.form.support.RecordBuilder;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;

public class SaveDraftOperation extends AbstractOperation<String> {

	private final Logger log = LogUtils.getLogger(SaveDraftOperation.class,
			true);

	@Override
	public String execute(CommandContext commandContext) {
		String taskId = getParamValue(CONSTANTS.PROCESS_PARAMETER_TASK_ID);
		String processDefinitionId = getParamValue(CONSTANTS.PROCESS_PARAMETER_BPM_DEFINITION_ID);
		String businessKey = getParamValue(CONSTANTS.PROCESS_PARAMETER_BUSINESS_KEY);
		KeyValue keyValue = getKeyValue();

		if (this.notEmpty(taskId)) {
			// TODO - 如果是任务草稿，直接通过processInstanceId获得record，更新数据
			Task task = getProcessEngine().getTaskService().createTaskQuery()
					.taskId(taskId).singleResult();

			if (task == null) {
				throw new IllegalStateException("任务不存在");
			}

			String processInstanceId = task.getProcessInstanceId();
			Record record = keyValue.findByRef(processInstanceId);

			// TODO

		} else if (this.notEmpty(businessKey)) {
			// 如果是流程草稿，直接通过businessKey获得record，更新数据
			Record record = keyValue.findByCode(businessKey);

			record = new RecordBuilder().build(record,
					CONSTANTS.PROCESS_STATUS_DRAFT_PROCESS,
					this.getFilteredParameters());

			keyValue.save(record);
		} else {
			// 如果是第一次保存草稿，肯定是流程草稿，先初始化record，再保存数据
			Record record = new RecordBuilder().build(processDefinitionId,
					CONSTANTS.PROCESS_STATUS_DRAFT_PROCESS,
					this.getFilteredParameters(),
					(String) ShiroUtils.getAttribute("user_id"));

			// 保存业务实体.
			keyValue.save(record);

			// 获取BusinessKey.
			businessKey = record.getCode();
		}
		return businessKey;
	}

	public boolean notEmpty(String str) {
		return (str != null) && (!"".equals(str));
	}

	public KeyValue getKeyValue() {
		return ApplicationContextHelper.getBean(KeyValue.class);
	}
}

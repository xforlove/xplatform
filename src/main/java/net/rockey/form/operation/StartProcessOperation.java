package net.rockey.form.operation;

import java.util.Map;

import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.ShiroUtils;
import net.rockey.form.keyvalue.KeyValue;
import net.rockey.form.keyvalue.Record;
import net.rockey.form.support.RecordBuilder;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartProcessOperation extends AbstractOperation<String> {

	private static final Logger log = LoggerFactory
			.getLogger(StartProcessOperation.class);

	@Override
	public String execute(CommandContext commandContext) {
		ProcessEngine processEngine = getProcessEngine();
		String bpmProcessId = getParamValue(CONSTANTS.PROCESS_PARAMETER_BPM_PROCESS_ID);

		KeyValue keyValue = getKeyValue();

		BpmProcess bpmProcess = getBpmProcessManager().get(
				Long.parseLong(bpmProcessId));

		String processDefinitionKey = bpmProcess.getBpmConfBase()
				.getProcessDefinitionKey();

		log.info("processDefinitionKey : {}", processDefinitionKey);

		// 先保存草稿
		String businessKey = new SaveDraftOperation().execute(this
				.getParameters());

		// 先设置登录用户
		IdentityService identityService = processEngine.getIdentityService();
		identityService.setAuthenticatedUserId((String) ShiroUtils
				.getAttribute("user_id"));

		// 构建流程参数
		Map<String, Object> processParameters = this.getProcessParameters();

		log.info("processParameters : {}", processParameters);

		// 启动流程
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey(processDefinitionKey, businessKey,
						processParameters);

		String processInstanceId = processInstance.getProcessInstanceId();

		// 将流程实例号提交给业务实体
		Record record = keyValue.findByCode(businessKey);
		record.setRef(processInstanceId);

		record = new RecordBuilder().build(record,
				CONSTANTS.PROCESS_STATUS_RUNNING, processInstanceId);

		// Save business entity.
		keyValue.save(record);

		return processInstanceId;
	}

	public BpmProcessManager getBpmProcessManager() {
		return ApplicationContextHelper.getBean(BpmProcessManager.class);
	}

	public KeyValue getKeyValue() {
		return ApplicationContextHelper.getBean(KeyValue.class);
	}

}

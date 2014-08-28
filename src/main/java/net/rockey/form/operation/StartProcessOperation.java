package net.rockey.form.operation;

import java.util.HashMap;
import java.util.Map;

import net.rockey.auth.manager.UserManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.manager.BpmTaskConfManager;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CPublic;
import net.rockey.core.util.LogUtils;
import net.rockey.form.manager.RecordManager;
import net.rockey.form.model.Prop;
import net.rockey.form.model.Record;
import net.rockey.form.support.RecordBuilder;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;

public class StartProcessOperation extends AbstractOperation<Void> {

	private final Logger log = LogUtils.getLogger(StartProcessOperation.class,
			true);

	public static final String OPERATION_BUSINESS_KEY = "businessKey";
	public static final String OPERATION_BPM_PROCESS_ID = "bpmProcessId";
	public static final String OPERATION_TASK_ID = "taskId";
	public static final String STATUS_RUNNING = "RUNNING";

	@Override
	public Void execute(CommandContext commandContext) {
		ProcessEngine processEngine = getProcessEngine();
		String bpmProcessId = getParamValue(OPERATION_BPM_PROCESS_ID);

		BpmProcess bpmProcess = getBpmProcessManager().get(
				Long.parseLong(bpmProcessId));

		String processDefinitionKey = bpmProcess.getBpmConfBase()
				.getProcessDefinitionKey();

		// 先保存草稿
		String businessKey = new SaveDraftOperation().execute(this
				.getParameters());

		// 先设置登录用户
		IdentityService identityService = processEngine.getIdentityService();
		identityService.setAuthenticatedUserId(this.getCurrentUserId()
				.toString());

		Record record = this.getRecordManager()
				.get(Long.parseLong(businessKey));

		Map<String, Object> processParameters = new HashMap<String, Object>();

		for (Prop prop : record.getProps()) {
			if (OPERATION_BUSINESS_KEY.equals(prop.getCode())) {
				prop.setValue(record.getId().toString());
			}
			processParameters.put(prop.getCode(), prop.getValue());
		}

		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey(processDefinitionKey, businessKey,
						processParameters);

		record = new RecordBuilder().build(record, STATUS_RUNNING,
				processInstance.getId());

		this.getRecordManager().save(record);

		return null;
	}

	public BpmTaskConfManager getBpmTaskConfManager() {
		return ApplicationContextHelper.getBean(BpmTaskConfManager.class);
	}

	public BpmProcessManager getBpmProcessManager() {
		return ApplicationContextHelper.getBean(BpmProcessManager.class);
	}

	public UserManager getUserManager() {
		return ApplicationContextHelper.getBean(UserManager.class);
	}

	public RecordManager getRecordManager() {
		return ApplicationContextHelper.getBean(RecordManager.class);
	}
}

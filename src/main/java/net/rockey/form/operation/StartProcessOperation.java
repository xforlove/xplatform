package net.rockey.form.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.rockey.bpm.manager.BpmLeaveApplyLogManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmLeaveApplyLog;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.ShiroUtils;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;

public class StartProcessOperation extends AbstractOperation<String> {

	private final Logger log = LogUtils.getLogger(StartProcessOperation.class,
			true);

	public static final String OPERATION_BUSINESS_KEY = "businessKey";
	public static final String OPERATION_BPM_PROCESS_ID = "bpmProcessId";
	public static final String OPERATION_TASK_ID = "taskId";
	public static final String STATUS_RUNNING = "RUNNING";

	@Override
	public String execute(CommandContext commandContext) {
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
		identityService.setAuthenticatedUserId((String) ShiroUtils
				.getAttribute("user_id"));

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

		// 启动流程
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey(processDefinitionKey, businessKey,
						processParameters);

		String processInstanceId = processInstance.getProcessInstanceId();

		log.debug("businessKey : " + businessKey + ", processInstanceId : "
				+ processInstanceId);

		// 将流程实例号提交给业务实体
		BpmLeaveApplyLog applyLog = this.getBpmLeaveApplyLogManager().get(
				Long.parseLong(businessKey));
		applyLog.setProcessInstanceId(processInstanceId);
		applyLog.setProcessStatFlag(CONSTANTS.PROCESS_STATUS_RUNNING);
		
		this.getBpmLeaveApplyLogManager().save(applyLog);

		return processInstanceId;
	}

	public BpmProcessManager getBpmProcessManager() {
		return ApplicationContextHelper.getBean(BpmProcessManager.class);
	}

	public BpmLeaveApplyLogManager getBpmLeaveApplyLogManager() {
		return ApplicationContextHelper.getBean(BpmLeaveApplyLogManager.class);
	}
}

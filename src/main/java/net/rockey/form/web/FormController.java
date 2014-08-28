package net.rockey.form.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.rockey.bpm.FormInfo;
import net.rockey.bpm.cmd.FindStartFormCmd;
import net.rockey.bpm.manager.BpmConfFormManager;
import net.rockey.bpm.manager.BpmConfOperationManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.manager.BpmTaskConfManager;
import net.rockey.bpm.manager.BpmVocationApplyLogManager;
import net.rockey.bpm.model.BpmConfForm;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.bpm.model.BpmVocationApplyLog;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.LogUtils;
import net.rockey.form.manager.RecordManager;
import net.rockey.form.model.Record;
import net.rockey.form.operation.CompleteTaskOperation;
import net.rockey.form.operation.StartProcessOperation;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("form")
public class FormController {

	private final Logger log = LogUtils.getLogger(FormController.class, true);

	public static final int STATUS_RUNNING = 2;

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private BpmProcessManager bpmProcessManager;

	@Autowired
	private BpmTaskConfManager bpmTaskConfManager;

	@Autowired
	private BpmConfOperationManager bpmConfOperationManager;

	@Autowired
	private BpmConfFormManager bpmConfFormManager;

	@Autowired
	private RecordManager recordManager;

	@Autowired
	private BpmVocationApplyLogManager bpmVocationApplyLogManager;

	@Autowired
	private MessageHelper messageHelper;

	/**
	 * 显示启动流程的表单.
	 */
	@RequestMapping("form-viewStartForm")
	public String viewStartForm(
			@RequestParam(value = "bpmProcessId", required = true) Long bpmProcessId,
			@RequestParam(value = "businessKey", required = false) String businessKey,
			RedirectAttributes redirectAttributes, Model model) {
		redirectAttributes.addAttribute("bpmProcessId", bpmProcessId);
		redirectAttributes.addAttribute("businessKey", businessKey);

		BpmProcess bpmProcess = bpmProcessManager.get(bpmProcessId);
		String processDefinitionId = bpmProcess.getBpmConfBase()
				.getProcessDefinitionId();

		redirectAttributes.addAttribute("businessType", bpmProcess.getCode());

		FormInfo formInfo = processEngine.getManagementService()
				.executeCommand(new FindStartFormCmd(processDefinitionId));

		redirectAttributes.addAttribute("processDefinitionId",
				formInfo.getProcessDefinitionId());
		redirectAttributes.addAttribute("autoCompleteFirstTask",
				formInfo.isAutoCompleteFirstTask());

		String nextStep = null;

		if (formInfo.isFormExists()) {
			// 如果找到了form，就显示表单
			if (Integer.valueOf(1).equals(bpmProcess.getNeedTaskConf())) {
				// 如果需要配置负责人
				nextStep = "taskConf";
			} else {
				nextStep = "startProcessInstance";
			}

			redirectAttributes.addAttribute("nextStep", nextStep);

			List<BpmConfForm> bpmConfForms = bpmConfFormManager
					.find("from BpmConfForm where bpmConfNode.bpmConfBase.processDefinitionId=? and bpmConfNode.code=?",
							formInfo.getProcessDefinitionId(),
							formInfo.getActivityId());

			if (!bpmConfForms.isEmpty()) {
				if (Integer.valueOf(1).equals(bpmConfForms.get(0).getType())) {
					String redirectUrl = bpmConfForms.get(0).getValue()
							+ "?processDefinitionId="
							+ formInfo.getProcessDefinitionId();

					return "redirect:" + redirectUrl;
				}
			}

			String redirectUrl = formInfo.getFormKey()
					+ "?processDefinitionId="
					+ formInfo.getProcessDefinitionId();

			return "redirect:" + redirectUrl;

			// TODO - return "form/form-viewStartForm";
		} else {
			// TODO - 如果没找到form，就判断是否配置负责人
			return null;
		}
	}

	/**
	 * 发起流程
	 * 
	 * @param multiValueMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("form-startProcessInstance")
	public String startProcessInstance(
			@RequestParam Map<String, Object> parameterMap,
			RedirectAttributes redirectAttributes, Model model)
			throws Exception {

		new StartProcessOperation().execute(parameterMap);

		messageHelper.addFlashMessage(redirectAttributes, "流程已发起");

		return "redirect:/bpm/workspace-home.do";
	}

	/**
	 * 显示任务表单.
	 */
	@RequestMapping("form-viewTaskForm")
	public String viewTaskForm(@RequestParam("taskId") String taskId,
			Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		if (task == null) {
			messageHelper.addFlashMessage(redirectAttributes, "任务不存在");
			return "redirect:/bpm/workspace-listPersonalTasks.do";
		}

		FormService formService = processEngine.getFormService();
		String taskFormKey = formService.getTaskFormKey(
				task.getProcessDefinitionId(), task.getTaskDefinitionKey());

		redirectAttributes.addAttribute("taskId", taskId);

		// List<BpmConfOperation> bpmConfOperations = bpmConfOperationManager
		// .find("from BpmConfOperation where bpmConfNode.bpmConfBase.processDefinitionId=? and bpmConfNode.code=?",
		// task.getProcessDefinitionId(),
		// task.getTaskDefinitionKey());
		//
		// for (BpmConfOperation bpmConfOperation : bpmConfOperations) {
		// formInfo.getButtons().add(bpmConfOperation.getValue());
		// }

		String processDefinitionId = task.getProcessDefinitionId();
		String activitiyId = task.getTaskDefinitionKey();
		List<BpmConfForm> bpmConfForms = bpmConfFormManager
				.find("from BpmConfForm where bpmConfNode.bpmConfBase.processDefinitionId=? and bpmConfNode.code=?",
						processDefinitionId, activitiyId);

		if (!bpmConfForms.isEmpty()) {
			if (Integer.valueOf(1).equals(bpmConfForms.get(0).getType())) {
				String redirectUrl = bpmConfForms.get(0).getValue()
						+ "?taskId=" + taskId;

				return "redirect:" + redirectUrl;
			}
		}

		ProcessInstance processInstance = processEngine.getRuntimeService()
				.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult();

		String businessKey = processInstance.getBusinessKey();

		Record record = (Record) recordManager.find(businessKey);

		BpmVocationApplyLog applyLog = bpmVocationApplyLogManager.get(record
				.getApplySeqId());

		redirectAttributes.addAttribute("applyLog", applyLog);

		String redirectUrl = taskFormKey + "?taskId=" + taskId;

		return "redirect:" + redirectUrl;

		// TODO - return "form/form-viewTaskForm";
	}

	/**
	 * 完成任务.
	 */
	@RequestMapping("form-completeTask")
	public String completeTask(@RequestParam Map<String, Object> parameterMap,
			RedirectAttributes redirectAttributes) throws Exception {

		try {
			new CompleteTaskOperation().execute(parameterMap);
		} catch (IllegalStateException ex) {
			log.error(ex.getMessage(), ex);
			messageHelper.addFlashMessage(redirectAttributes, ex.getMessage());

			return "redirect:/bpm/workspace-listPersonalTasks.do";
		}

		return "form/form-completeTask";
	}
}

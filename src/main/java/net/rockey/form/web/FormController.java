package net.rockey.form.web;

import java.util.Map;

import net.rockey.bpm.FormInfo;
import net.rockey.bpm.cmd.FindStartFormCmd;
import net.rockey.bpm.manager.BpmLeaveApplyLogManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmLeaveApplyLog;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.LogUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("form")
public class FormController {

	private final Logger log = LogUtils.getLogger(FormController.class, true);

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private BpmProcessManager bpmProcessManager;

	@Autowired
	private BpmLeaveApplyLogManager bpmLeaveApplyLogManager;

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

		if (formInfo.isFormExists()) {

			return "redirect:" + formInfo.getFormKey();
			// TODO - return "form/form-viewStartForm";
		} else {
			// TODO
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

		String processInstanceId = new StartProcessOperation()
				.execute(parameterMap);

		messageHelper.addFlashMessage(redirectAttributes, "流程已发起，流程实例号["
				+ processInstanceId + "]");

		return "redirect:../bpm/workspace-home.do";
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
		
		redirectAttributes.addAttribute("taskId", task.getId());

		// 获得流程定义时配置的taskFormKey
		FormService formService = processEngine.getFormService();
		String taskFormKey = formService.getTaskFormKey(
				task.getProcessDefinitionId(), task.getTaskDefinitionKey());

		log.debug("taskFormKey : " + taskFormKey);
		
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult();

		String businessKey = processInstance.getBusinessKey();
		
		redirectAttributes.addAttribute("businessKey", businessKey);

		BpmLeaveApplyLog applyLog = bpmLeaveApplyLogManager.get(Long
				.parseLong(businessKey));

		// TODO - 以后考虑通过Ajax直接加载至页面
		redirectAttributes.addAttribute("type", applyLog.getType());
		redirectAttributes.addAttribute("creator", applyLog.getCreator());
		
		redirectAttributes.addAttribute("applySeqId", applyLog.getId());
		
		String redirectUrl = taskFormKey;

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
			messageHelper.addFlashMessage(redirectAttributes, "操作成功");
		} catch (IllegalStateException ex) {
			log.error(ex.getMessage(), ex);
			messageHelper.addFlashMessage(redirectAttributes, ex.getMessage());
		}

		return "redirect:../bpm/workspace-listPersonalTasks.do";

		// TODO - return "form/form-completeTask";
	}
}

package net.rockey.form.web;

import java.util.List;

import net.rockey.bpm.FormInfo;
import net.rockey.bpm.cmd.FindStartFormCmd;
import net.rockey.bpm.manager.BpmConfFormManager;
import net.rockey.bpm.manager.BpmConfOperationManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.manager.BpmTaskConfManager;
import net.rockey.bpm.model.BpmConfForm;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.util.LogUtils;

import org.activiti.engine.ProcessEngine;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	/**
	 * 显示启动流程的表单.
	 */
	@RequestMapping("form-viewStartForm")
	public String viewStartForm(
			@RequestParam(value = "bpmProcessId", required = true) Long bpmProcessId,
			@RequestParam(value = "businessKey", required = false) String businessKey,
			Model model) {
		model.addAttribute("bpmProcessId", bpmProcessId);
		model.addAttribute("businessKey", businessKey);

		BpmProcess bpmProcess = bpmProcessManager.get(bpmProcessId);
		String processDefinitionId = bpmProcess.getBpmConfBase()
				.getProcessDefinitionId();

		FormInfo formInfo = processEngine.getManagementService()
				.executeCommand(new FindStartFormCmd(processDefinitionId));

		model.addAttribute("formInfo", formInfo);

		String nextStep = null;

		if (formInfo.isFormExists()) {
			// 如果找到了form，就显示表单
			if (Integer.valueOf(1).equals(bpmProcess.getNeedTaskConf())) {
				// 如果需要配置负责人
				nextStep = "taskConf";
			} else {
				nextStep = "confirmStartProcess";
			}

			model.addAttribute("nextStep", nextStep);

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

			// TODO

			return "form/form-viewStartForm";

		} else {
			// TODO - 如果没找到form，就判断是否配置负责人
			return null;
		}

	}
}

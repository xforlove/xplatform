package net.rockey.bpm.web;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.rockey.bpm.cmd.ProcessDefinitionDiagramCmd;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("bpm")
public class ConsoleController {

	@Autowired
	private ProcessEngine processEngine;

	/**
	 * 显示流程实例列表.
	 */
	@RequestMapping("console-listProcessInstances")
	public String listProcessInstances(Model model) {
		RuntimeService runtimeService = processEngine.getRuntimeService();

		List<ProcessInstance> processInstances = runtimeService
				.createProcessInstanceQuery().list();
		model.addAttribute("processInstances", processInstances);

		return "bpm/console-listProcessInstances";
	}

	/**
	 * 显示流程定义图形.
	 */
	@RequestMapping("console-graphProcessDefinition")
	public void graphProcessDefinition(
			@RequestParam("processDefinitionId") String processDefinitionId,
			HttpServletResponse response) throws Exception {
		Command<InputStream> cmd = new ProcessDefinitionDiagramCmd(
				processDefinitionId);

		InputStream is = processEngine.getManagementService().executeCommand(
				cmd);
		response.setContentType("image/png");

		IOUtils.copy(is, response.getOutputStream());
	}

}

package net.rockey.bpm.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.rockey.bpm.cmd.ProcessDefinitionDiagramCmd;
import net.rockey.bpm.cmd.SyncProcessCmd;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
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
	 * 发布流程.
	 */
	@RequestMapping("console-deploy")
	public String deploy(@RequestParam("xml") String xml) throws Exception {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ByteArrayInputStream bais = new ByteArrayInputStream(
				xml.getBytes("UTF-8"));
		Deployment deployment = repositoryService.createDeployment()
				.addInputStream("process.bpmn20.xml", bais).deploy();
		List<ProcessDefinition> processDefinitions = repositoryService
				.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).list();

		for (ProcessDefinition processDefinition : processDefinitions) {
			processEngine.getManagementService().executeCommand(
					new SyncProcessCmd(processDefinition.getId()));
		}

		return "redirect:/bpm/console-listProcessDefinitions.do";
	}

	/**
	 * 显示流程定义列表.
	 */
	@RequestMapping("console-listProcessDefinitions")
	public String listProcessDefinitions(Model model) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		List<ProcessDefinition> processDefinitions = repositoryService
				.createProcessDefinitionQuery().list();
		model.addAttribute("processDefinitions", processDefinitions);

		return "bpm/console-listProcessDefinitions";
	}

	/**
	 * 显示流程实例列表.
	 */
	@RequestMapping("console-listProcessInstances")
	public String listProcessInstances(Model model) {
		RuntimeService runtimeService = processEngine.getRuntimeService();

		List<ProcessInstance> processInstances = runtimeService
				.createProcessInstanceQuery().orderByProcessInstanceId().desc()
				.list();
		
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

package net.rockey.bpm.web;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.rockey.bpm.cmd.HistoryProcessInstanceDiagramCmd;
import net.rockey.bpm.cmd.ProcessDefinitionDiagramCmd;
import net.rockey.bpm.manager.BpmCategoryManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.util.LogUtils;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("bpm")
public class WorkspaceController {

	private final Logger log = LogUtils.getLogger(WorkspaceController.class,
			true);

	@Autowired
	private BpmCategoryManager bpmCategoryManager;

	@Autowired
	private BpmProcessManager bpmProcessManager;

	@Autowired
	private ProcessEngine processEngine;

	@RequestMapping("workspace-home")
	public String home(Model model) {

		List<BpmProcess> bpmProcesses = bpmProcessManager.getAll("priority",
				true);

		model.addAttribute("list", bpmProcesses);

		return "bpm/workspace-home";
	}

	@RequestMapping("workspace-graphProcessDefinition")
	public void graphProcessDefinition(
			@RequestParam("bpmProcessId") Long bpmProcessId,
			HttpServletResponse response) throws Exception {
		BpmProcess bpmProcess = bpmProcessManager.get(bpmProcessId);
		String processDefinitionId = bpmProcess.getBpmConfBase()
				.getProcessDefinitionId();

		Command<InputStream> cmd = null;
		cmd = new ProcessDefinitionDiagramCmd(processDefinitionId);

		InputStream is = processEngine.getManagementService().executeCommand(
				cmd);
		response.setContentType("image/png");

		IOUtils.copy(is, response.getOutputStream());
	}

	/**
	 * 流程跟踪
	 * 
	 * @throws Exception
	 */
	@RequestMapping("workspace-graphHistoryProcessInstance")
	public void graphHistoryProcessInstance(
			@RequestParam("processInstanceId") String processInstanceId,
			HttpServletResponse response) throws Exception {
		Command<InputStream> cmd = new HistoryProcessInstanceDiagramCmd(
				processInstanceId);

		InputStream is = processEngine.getManagementService().executeCommand(
				cmd);
		response.setContentType("image/png");

		int len = 0;
		byte[] b = new byte[1024];

		while ((len = is.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 待办任务（个人任务）
	 * 
	 * @return
	 */
	@RequestMapping("workspace-listPersonalTasks")
	public String listPersonalTasks(Model model) {
		TaskService taskService = processEngine.getTaskService();
		String userId = SecurityUtils.getSubject().getSession()
				.getAttribute("user_id").toString();

		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId)
				.active().orderByProcessInstanceId().list();
		model.addAttribute("tasks", tasks);

		return "bpm/workspace-listPersonalTasks";
	}

	/**
	 * 已办任务（历史任务）
	 * 
	 * @return
	 */
	@RequestMapping("workspace-listHistoryTasks")
	public String listHistoryTasks(Model model) {
		HistoryService historyService = processEngine.getHistoryService();
		String userId = SecurityUtils.getSubject().getSession()
				.getAttribute("user_id").toString();
		List<HistoricTaskInstance> historicTasks = historyService
				.createHistoricTaskInstanceQuery().taskAssignee(userId)
				.finished().orderByProcessInstanceId().list();
		model.addAttribute("historicTasks", historicTasks);

		return "bpm/workspace-listHistoryTasks";
	}

	/**
	 * 查看历史【包含流程跟踪、任务列表（完成和未完成）、流程变量】
	 * 
	 * @return
	 */
	@RequestMapping("workspace-viewHistory")
	public String viewHistory(
			@RequestParam("processInstanceId") String processInstanceId,
			Model model) {
		HistoryService historyService = processEngine.getHistoryService();
		List<HistoricTaskInstance> historicTasks = historyService
				.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).list();
		List<HistoricVariableInstance> historicVariableInstances = historyService
				.createHistoricVariableInstanceQuery()
				.processInstanceId(processInstanceId).orderByVariableName().list();
		model.addAttribute("historicTasks", historicTasks);
		model.addAttribute("historicVariableInstances",
				historicVariableInstances);

		return "bpm/workspace-viewHistory";
	}
}

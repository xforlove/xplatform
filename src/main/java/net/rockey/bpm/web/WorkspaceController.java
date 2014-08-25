package net.rockey.bpm.web;

import java.util.List;

import net.rockey.bpm.manager.BpmCategoryManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.util.LogUtils;

import org.activiti.engine.ProcessEngine;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("workspace")
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
}

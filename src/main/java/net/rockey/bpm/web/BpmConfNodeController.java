package net.rockey.bpm.web;

import java.util.List;

import net.rockey.bpm.manager.BpmConfBaseManager;
import net.rockey.bpm.manager.BpmConfNodeManager;
import net.rockey.bpm.manager.BpmConfUserManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmConfBase;
import net.rockey.bpm.model.BpmConfNode;
import net.rockey.core.mapper.BeanMapper;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("bpm")
public class BpmConfNodeController {

	@Autowired
	private BpmConfUserManager bpmConfUserManager;

	@Autowired
	private BpmConfNodeManager bpmConfNodeManager;

	@Autowired
	private BpmConfBaseManager bpmConfBaseManager;

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private BpmProcessManager bpmProcessManager;

	private BeanMapper beanMapper = new BeanMapper();

	@RequestMapping("bpm-conf-node-list")
	public String list(@RequestParam("bpmConfBaseId") Long bpmConfBaseId,
			Model model) {
		BpmConfBase bpmConfBase = bpmConfBaseManager.get(bpmConfBaseId);

		List<BpmConfNode> bpmConfNodes = bpmConfNodeManager.findBy(
				"bpmConfBase", bpmConfBase);

		model.addAttribute("bpmConfNodes", bpmConfNodes);

		return "bpm/bpm-conf-node-list";
	}

}

package net.rockey.bpm.web;

import java.util.Map;

import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.LogUtils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("leave")
public class BpmLeaveController {

	private static final Logger log = LogUtils.getLogger(
			BpmLeaveController.class, true);

	@RequestMapping("apply")
	public String apply(@RequestParam Map<String, Object> parameterMap,
			RedirectAttributes redirectAttributes, Model model) {

		String processDefinitionId = (String) parameterMap
				.get(CONSTANTS.PROCESS_PARAMETER_BPM_DEFINITION_ID);

		log.info("processDefinitionId : " + processDefinitionId);

		String bpmProcessId = (String) parameterMap
				.get(CONSTANTS.PROCESS_PARAMETER_BPM_PROCESS_ID);

		log.info("bpmProcessId : " + bpmProcessId);
		
		model.addAttribute("processDefinitionId", processDefinitionId);
		model.addAttribute("bpmProcessId", bpmProcessId);
		
		return "bpm/vocation-request";
	}
}

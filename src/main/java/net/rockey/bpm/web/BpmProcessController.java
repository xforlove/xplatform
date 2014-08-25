package net.rockey.bpm.web;

import java.util.List;
import java.util.Map;

import net.rockey.bpm.manager.BpmCategoryManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmCategory;
import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.StringUtils;
import net.rockey.ext.export.Exportor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("bpm")
public class BpmProcessController {

	private final Logger log = LogUtils.getLogger(BpmProcessController.class,
			true);

	@Autowired
	private BpmCategoryManager bpmCategoryManager;

	@Autowired
	private BpmProcessManager bpmProcessManager;

	@Autowired
	private MessageHelper messageHelper;

	@Autowired
	private Exportor exportor;

	private BeanMapper beanMapper = new BeanMapper();

	@RequestMapping("process-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String procName = ParamUtils.getString(parameterMap, "pName");

		List<BpmProcess> processes;
		if (StringUtils.isEmpty(procName)) {
			processes = (List<BpmProcess>) bpmProcessManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			processes = (List<BpmProcess>) bpmProcessManager.findByLike("name",
					"%" + procName + "%");
		}

		page.setResult(processes);
		model.addAttribute("page", page);

		return "bpm/process-list";
	}

	@RequestMapping("process-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {
		if (id != null) {
			BpmProcess bpmProcess = bpmProcessManager.get(id);
			model.addAttribute("model", bpmProcess);
		}

		List<BpmCategory> bpmCategories = bpmCategoryManager.getAll();
		model.addAttribute("bpmCategories", bpmCategories);

		return "bpm/process-input";
	}

}

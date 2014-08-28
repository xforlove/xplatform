package net.rockey.bpm.web;

import java.util.List;

import net.rockey.bpm.manager.BpmConfFormManager;
import net.rockey.bpm.manager.BpmConfNodeManager;
import net.rockey.bpm.manager.BpmProcessManager;
import net.rockey.bpm.model.BpmConfForm;
import net.rockey.bpm.model.BpmConfNode;
import net.rockey.core.mapper.BeanMapper;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("bpm")
public class BpmConfFormController {

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private BpmProcessManager bpmProcessManager;

	@Autowired
	private BpmConfNodeManager bpmConfNodeManager;

	@Autowired
	private BpmConfFormManager bpmConfFormManager;

	private BeanMapper beanMapper = new BeanMapper();

	@RequestMapping("bpm-conf-form-list")
	public String list(@RequestParam("bpmConfNodeId") Long bpmConfNodeId,
			Model model) {
		BpmConfNode bpmConfNode = bpmConfNodeManager.get(bpmConfNodeId);
		Long bpmConfBaseId = bpmConfNode.getBpmConfBase().getId();
		List<BpmConfForm> bpmConfForms = bpmConfFormManager.findBy(
				"bpmConfNode", bpmConfNode);
		model.addAttribute("bpmConfBaseId", bpmConfBaseId);
		model.addAttribute("bpmConfForms", bpmConfForms);

		return "bpm/bpm-conf-form-list";
	}

	@RequestMapping("bpm-conf-form-save")
	public String save(@ModelAttribute BpmConfForm bpmConfForm,
			@RequestParam("bpmConfNodeId") Long bpmConfNodeId) {
		BpmConfForm dest = bpmConfFormManager.findUnique(
				"from BpmConfForm where bpmConfNode.id=?", bpmConfNodeId);

		if (dest == null) {
			// 如果不存在，就创建一个
			bpmConfForm.setBpmConfNode(bpmConfNodeManager.get(bpmConfNodeId));
			bpmConfForm.setStatus(1);
			bpmConfFormManager.save(bpmConfForm);
		} else {
			dest.setValue(bpmConfForm.getValue());
			dest.setType(bpmConfForm.getType());
			dest.setStatus(1);
			bpmConfFormManager.save(dest);
		}

		return "redirect:/bpm/bpm-conf-form-list.do?bpmConfNodeId="
				+ bpmConfNodeId;
	}
	
	@RequestMapping("bpm-conf-form-remove")
	public String remove(@RequestParam("id") Long id) {
		BpmConfForm bpmConfForm = bpmConfFormManager.get(id);
		Long bpmConfNodeId = bpmConfForm.getBpmConfNode().getId();

		if (bpmConfForm.getStatus() == 0) {
			bpmConfForm.setStatus(2);
			bpmConfFormManager.save(bpmConfForm);
		} else if (bpmConfForm.getStatus() == 2) {
			bpmConfForm.setStatus(1);
			bpmConfFormManager.save(bpmConfForm);
		} else if (bpmConfForm.getStatus() == 1) {
			if (bpmConfForm.getOriginValue() == null) {
				bpmConfFormManager.remove(bpmConfForm);
			} else {
				bpmConfForm.setStatus(0);
				bpmConfForm.setValue(bpmConfForm.getOriginValue());
				bpmConfForm.setType(bpmConfForm.getOriginType());
				bpmConfFormManager.save(bpmConfForm);
			}
		}

		return "redirect:/bpm/bpm-conf-form-list.do?bpmConfNodeId="
				+ bpmConfNodeId;
	}

}

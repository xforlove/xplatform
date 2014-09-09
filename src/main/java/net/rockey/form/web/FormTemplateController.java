package net.rockey.form.web;

import java.util.List;

import net.rockey.core.spring.MessageHelper;
import net.rockey.form.manager.FormTemplateManager;
import net.rockey.form.model.FormTemplate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("form-template")
public class FormTemplateController {

	private static final Logger log = LoggerFactory
			.getLogger(FormTemplateController.class);

	@Autowired
	private FormTemplateManager formTemplateManager;

	@Autowired
	private MessageHelper messageHelper;

	@RequestMapping("form-template-list")
	public String list(
			@RequestParam(value = "name", required = false) String name,
			RedirectAttributes redirectAttributes, Model model) {

		log.debug("name : {}", name);

		List<FormTemplate> forms;
		if (StringUtils.isEmpty(name)) {
			forms = (List<FormTemplate>) formTemplateManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			forms = (List<FormTemplate>) formTemplateManager.findByLike("name",
					"%" + name + "%");
		}

		model.addAttribute("forms", forms);

		return "form/form-template-list";
	}

	@RequestMapping("form-template-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {

		if (id != null) {
			FormTemplate item = formTemplateManager.load(id);
			model.addAttribute("model", item);
		}

		return "form/form-template-input";
	}

	@RequestMapping("form-template-save")
	public String save(@ModelAttribute FormTemplate formTemplate,
			RedirectAttributes redirectAttributes, Model model) {

		Long id = formTemplate.getId();

		FormTemplate form = (id == null) ? (new FormTemplate())
				: (formTemplateManager.get(id));

		form.setCode(formTemplate.getCode());
		form.setName(formTemplate.getName());
		form.setUrl(formTemplate.getUrl());
		form.setDescn(formTemplate.getDescn());

		formTemplateManager.save(form);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:form-template-list.do";
	}

}

package net.rockey.bpm.web;

import java.util.List;
import java.util.Map;

import net.rockey.bpm.manager.BpmCategoryManager;
import net.rockey.bpm.model.BpmCategory;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("bpm")
public class BpmCategoryController {

	private final Logger log = LogUtils.getLogger(BpmCategoryController.class,
			true);

	@Autowired
	private BpmCategoryManager bpmCategoryManager;

	@Autowired
	private MessageHelper messageHelper;

	@Autowired
	private Exportor exportor;

	private BeanMapper beanMapper = new BeanMapper();

	@RequestMapping("bpm-category-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String name = ParamUtils.getString(parameterMap, "name");

		List<BpmCategory> categories;
		if (StringUtils.isEmpty(name)) {
			categories = (List<BpmCategory>) bpmCategoryManager
					.find(" from BpmCategory ");
		} else {
			/* 字符串两端全模糊匹配 */
			categories = (List<BpmCategory>) bpmCategoryManager.findByLike(
					"name", "%" + name + "%");
		}

		page.setResult(categories);
		model.addAttribute("page", page);

		return "bpm/bpm-category-list";
	}

	@RequestMapping("bpm-category-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {
		if (id != null) {

			BpmCategory category = bpmCategoryManager.load(id);

			model.addAttribute("model", category);
		}

		return "bpm/bpm-category-input";
	}

	@RequestMapping("category-save")
	public String save(@ModelAttribute BpmCategory bpmCategory,
			RedirectAttributes redirectAttributes) {
		BpmCategory dest = null;
		Long id = bpmCategory.getId();

		if (id != null) {
			dest = bpmCategoryManager.get(id);
			beanMapper.copy(bpmCategory, dest);
		} else {
			dest = bpmCategory;
		}

		bpmCategoryManager.save(dest);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:/bpm/category-list.do";
	}

}

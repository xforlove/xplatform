package net.rockey.bpm.web;

import java.util.List;
import java.util.Map;

import net.rockey.auth.model.AuthUser;
import net.rockey.auth.support.AuthUserDTO;
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

	private BpmCategoryManager bpmCategoryManager;

	private MessageHelper messageHelper;

	private Exportor exportor;

	private BeanMapper beanMapper = new BeanMapper();

	@RequestMapping("bpm-category-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String cataName = ParamUtils.getString(parameterMap, "cName");

		List<BpmCategory> categories;
		if (StringUtils.isEmpty(cataName)) {
			categories = (List<BpmCategory>) bpmCategoryManager
					.find(" from BpmCategory ");
		} else {
			/* 字符串两端全模糊匹配 */
			categories = (List<BpmCategory>) bpmCategoryManager.findByLike(
					"name", "%" + cataName + "%");
		}

		page.setResult(categories);
		model.addAttribute("page", page);

		return "bpm/bpm-category-list";
	}

	@RequestMapping("bpm-category-input")
	public String input(
			@RequestParam(value = "cid", required = false) Long cid, Model model) {
		if (cid != null) {

			BpmCategory category = bpmCategoryManager.load(cid);

			model.addAttribute("cid", cid);
			model.addAttribute("category", category);
		}

		return "bpm/bpm-category-input";
	}

	@RequestMapping("bpm-category-save")
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

		return "redirect:/bpm/bpm-category-list.do";
	}

}

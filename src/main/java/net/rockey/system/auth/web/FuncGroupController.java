package net.rockey.system.auth.web;

import java.util.List;
import java.util.Map;

import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.StringUtils;
import net.rockey.system.auth.manager.FuncGroupManager;
import net.rockey.system.auth.model.AppFuncGroup;
import net.rockey.system.auth.service.AppService;
import net.rockey.system.auth.support.AppFuncGroupDTO;

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
@RequestMapping("auth")
public class FuncGroupController {

	private static final Logger log = LoggerFactory
			.getLogger(FuncGroupController.class);

	@Autowired
	private FuncGroupManager funcGroupManager;

	@Autowired
	private AppService appService;

	@Autowired
	private MessageHelper messageHelper;

	private BeanMapper mapper = new BeanMapper();

	@RequestMapping("funcgroup-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String name = ParamUtils.getString(parameterMap, "name");

		List<AppFuncGroup> funcGrps;
		if (StringUtils.isEmpty(name)) {
			funcGrps = (List<AppFuncGroup>) funcGroupManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			funcGrps = (List<AppFuncGroup>) funcGroupManager.findByLike("name",
					"%" + name + "%");
		}

		model.addAttribute("funcGrps", funcGrps);

		return "auth/funcgroup-list";
	}

	@RequestMapping("funcgroup-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {

		if (id != null) {
			AppFuncGroup funcGrp = funcGroupManager.load(id);
			model.addAttribute("model", funcGrp);
		}

		return "auth/funcgroup-input";
	}

	@RequestMapping("funcgroup-save")
	public String save(@ModelAttribute AppFuncGroupDTO funcGrpDTO,
			RedirectAttributes redirectAttributes, Model model) {
		Long funcGrpId = funcGrpDTO.getId();
		String funcGrpName = funcGrpDTO.getName();
		String descn = funcGrpDTO.getDescn();

		AppFuncGroup funcGrp = (funcGrpId == null) ? (new AppFuncGroup())
				: (funcGroupManager.get(funcGrpId));

		funcGrp.setName(funcGrpName);
		funcGrp.setDescn(descn);

		funcGroupManager.save(funcGrp);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:funcgroup-list.do";
	}
}

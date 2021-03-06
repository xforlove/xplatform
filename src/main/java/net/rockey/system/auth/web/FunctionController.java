package net.rockey.system.auth.web;

import java.util.List;
import java.util.Map;

import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.StringUtils;
import net.rockey.system.auth.manager.FuncGroupManager;
import net.rockey.system.auth.manager.FunctionManager;
import net.rockey.system.auth.model.AppFuncGroup;
import net.rockey.system.auth.model.AppFunction;
import net.rockey.system.auth.service.AppService;
import net.rockey.system.auth.support.AppFunctionDTO;

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
public class FunctionController {

	private static final Logger log = LoggerFactory
			.getLogger(FunctionController.class);

	@Autowired
	private FuncGroupManager funcGrpManager;

	@Autowired
	private FunctionManager functionManager;

	@Autowired
	private AppService appService;

	@Autowired
	private MessageHelper messageHelper;

	@RequestMapping("function-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String name = ParamUtils.getString(parameterMap, "name");

		List<AppFunction> funcs;
		if (StringUtils.isEmpty(name)) {
			funcs = (List<AppFunction>) functionManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			funcs = (List<AppFunction>) functionManager.findByLike("name", "%"
					+ name + "%");
		}

		model.addAttribute("funcs", funcs);

		return "auth/function-list";
	}

	@RequestMapping("function-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {

		AppFunction function = null;

		if (id != null) {
			function = functionManager.load(id);
			model.addAttribute("model", function);
		}

		List<AppFuncGroup> funcGrps = funcGrpManager.getAll();
		model.addAttribute("funcGrps", funcGrps);

		return "auth/function-input";
	}

	@RequestMapping("function-save")
	public String save(@ModelAttribute AppFunctionDTO funcDTO,
			RedirectAttributes redirectAttributes, Model model) {
		Long funcId = funcDTO.getId();
		String funcCode = funcDTO.getCode();
		String funcName = funcDTO.getName();
		String funcAction = funcDTO.getAction();
		Long funcGrpId = funcDTO.getGroupId();
		String statFlag = funcDTO.getStatFlag();

		statFlag = statFlag == null ? CONSTANTS.STAT_FLAG_CLOSE : statFlag;

		AppFunction function = (funcId == null) ? (new AppFunction())
				: (functionManager.get(funcId));

		function.setCode(funcCode);
		function.setName(funcName);
		function.setAction(funcAction);
		function.setGroup(funcGrpManager.load(funcGrpId));
		function.setStatFlag(statFlag);

		functionManager.save(function);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:function-list.do";
	}
}

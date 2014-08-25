package net.rockey.app.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.rockey.app.manager.FuncGroupManager;
import net.rockey.app.manager.FunctionManager;
import net.rockey.app.model.AppFunction;
import net.rockey.app.service.AppService;
import net.rockey.app.support.AppFuncGroupDTO;
import net.rockey.app.support.AppFunctionDTO;
import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.StringUtils;
import net.rockey.core.util.ViewTransfer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("app")
public class FunctionController {

	private final Logger log = LogUtils.getLogger(FunctionController.class,
			true);

	@Autowired
	private FuncGroupManager funcGrpManager;

	@Autowired
	private FunctionManager functionManager;

	@Autowired
	private AppService appService;

	@Autowired
	private MessageHelper messageHelper;

	private BeanMapper mapper = new BeanMapper();

	@RequestMapping("function-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String name = ParamUtils.getString(parameterMap, "name");

		List<AppFunctionDTO> funcDtos = new ArrayList<AppFunctionDTO>();

		List<AppFunction> funcs;
		if (StringUtils.isEmpty(name)) {
			funcs = (List<AppFunction>) functionManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			funcs = (List<AppFunction>) functionManager.findByLike("name", "%"
					+ name + "%");
		}

		for (AppFunction func : funcs) {
			AppFunctionDTO dest = new AppFunctionDTO();
			mapper.copy(func, dest);
			dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(dest
					.getStatFlag()));
			funcDtos.add(dest);
		}

		page.setResult(funcDtos);
		model.addAttribute("page", page);

		return "app/function-list";
	}

	@RequestMapping("function-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {

		List<AppFuncGroupDTO> funcGrps = new ArrayList<AppFuncGroupDTO>();

		AppFunction function = null;

		if (id != null) {

			function = functionManager.load(id);

			AppFunctionDTO dest = new AppFunctionDTO();

			mapper.copy(function, dest);

			dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(function
					.getStatFlag()));

			model.addAttribute("id", id);
			model.addAttribute("func", dest);
		}

		funcGrps = appService
				.createFuncGroupListOnSelected(function == null ? null
						: function);
		model.addAttribute("funcGrps", funcGrps);

		return "app/function-input";
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

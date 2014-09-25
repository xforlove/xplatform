package net.rockey.system.param.web;

import java.util.List;
import java.util.Map;

import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.StringUtils;
import net.rockey.system.param.manager.SysparamManager;
import net.rockey.system.param.model.Sysparam;

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
@RequestMapping("param")
public class SysparamController {

	private static final Logger log = LoggerFactory
			.getLogger(SysparamController.class);

	@Autowired
	private SysparamManager sysparamManager;

	@Autowired
	private MessageHelper messageHelper;

	private BeanMapper beanMapper = new BeanMapper();

	@RequestMapping("param-list")
	public String list(@RequestParam Map<String, Object> parameterMap,
			Model model) {

		String name = ParamUtils.getString(parameterMap, "name");

		List<Sysparam> params;
		if (StringUtils.isEmpty(name)) {
			params = (List<Sysparam>) sysparamManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			params = (List<Sysparam>) sysparamManager.findByLike("name", "%"
					+ name + "%");
		}

		model.addAttribute("params", params);

		return "system/param-list";
	}

	@RequestMapping("param-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {

		if (id != null) {
			Sysparam sysparam = sysparamManager.load(id);
			model.addAttribute("model", sysparam);
		}

		return "system/param-input";
	}

	@RequestMapping("param-save")
	public String save(@ModelAttribute Sysparam sysparam,
			RedirectAttributes redirectAttributes, Model model) {
		Long id = sysparam.getId();
		String statFlag = sysparam.getStatFlag();

		log.debug("statFlag : {}", statFlag);

		statFlag = statFlag == null ? CONSTANTS.STAT_FLAG_CLOSE : statFlag;

		Sysparam dest = (id == null) ? (new Sysparam()) : (sysparamManager
				.load(id));

		beanMapper.copy(sysparam, dest);
		dest.setId(id);
		dest.setStatFlag(statFlag);

		sysparamManager.save(dest);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:param-list.do";
	}

}

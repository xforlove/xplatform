package net.rockey.system.auth.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.system.auth.manager.RoleManager;
import net.rockey.system.auth.model.AuthRole;
import net.rockey.system.auth.service.AuthService;
import net.rockey.system.auth.support.AppFunctionDTO;
import net.rockey.system.auth.support.AuthRoleDTO;

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
@RequestMapping("auth")
public class RoleController {

	private final static Logger log = LoggerFactory
			.getLogger(RoleController.class);

	@Autowired
	private RoleManager roleManager;

	@Autowired
	private AuthService authService;

	@Autowired
	private MessageHelper messageHelper;

	@RequestMapping("role-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		log.debug("parameterMap : {}", parameterMap);
		
		String name = ParamUtils.getString(parameterMap, "name");

		List<AuthRole> roles;
		if (StringUtils.isEmpty(name)) {
			roles = (List<AuthRole>) roleManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			roles = (List<AuthRole>) roleManager.findByLike("name", "%" + name
					+ "%");
		}

		model.addAttribute("roles", roles);

		return "auth/role-list";
	}

	@RequestMapping("role-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {

		if (id != null) {
			AuthRole role = roleManager.load(id);
			model.addAttribute("model", role);
		}

		return "auth/role-input";
	}

	@RequestMapping("role-save")
	public String save(@ModelAttribute AuthRoleDTO roleDTO,
			RedirectAttributes redirectAttributes, Model model) {
		Long roleId = roleDTO.getId();
		String roleCode = roleDTO.getCode();
		String roleName = roleDTO.getName();
		String statFlag = roleDTO.getStatFlag();
		String roleDesc = roleDTO.getDescn();

		statFlag = statFlag == null ? CONSTANTS.STAT_FLAG_CLOSE : statFlag;

		AuthRole role = (roleId == null) ? (new AuthRole()) : (roleManager
				.get(roleId));

		role.setCode(roleCode);
		role.setName(roleName);
		role.setStatFlag(statFlag);
		role.setDescn(roleDesc);

		roleManager.save(role);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:role-list.do";
	}

	@RequestMapping("role-res-input")
	public String configResInput(
			@RequestParam(value = "rid", required = true) Long rid, Model model) {

		List<AppFunctionDTO> funcs = new ArrayList<AppFunctionDTO>();

		if (rid != null) {

			AuthRole role = roleManager.get(rid);
			funcs = authService.createFunctionListOnSelected(role);

			model.addAttribute("rid", rid);
			model.addAttribute("role", role);
			model.addAttribute("funcs", funcs);

		}

		return "auth/role-res-input";
	}

	@RequestMapping("role-res-save")
	public String configResSave(
			@RequestParam(value = "rid", required = true) Long rid,
			@RequestParam(value = "funcIds", required = false) String funcIds,
			RedirectAttributes redirectAttributes, Model model) {

		log.debug("funcIds : {}", funcIds);

		if (StringUtils.isEmpty(funcIds)) {
			authService.clearRoleFunctionBy(roleManager.get(rid));
		} else {
			String[] resArr = StringUtils.splitByWholeSeparator(funcIds, ",");
			List<Long> funcList = new ArrayList<Long>();
			for (int i = 0; i < resArr.length; i++) {
				funcList.add(Long.parseLong(resArr[i]));
			}
			authService.configRoleFunction(rid, funcList, true);
		}

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:role-list.do";
	}

}

package net.rockey.auth.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.rockey.app.support.AppFunctionDTO;
import net.rockey.auth.manager.RoleManager;
import net.rockey.auth.model.AuthRole;
import net.rockey.auth.service.AuthService;
import net.rockey.auth.support.AuthRoleDTO;
import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.SequencePrefix;
import net.rockey.core.util.SequenceUtils;
import net.rockey.core.util.ViewTransfer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
public class RoleController {

	private final Logger log = LogUtils.getLogger(RoleController.class, true);

	@Autowired
	private RoleManager roleManager;

	@Autowired
	private AuthService authService;

	@Autowired
	private MessageHelper messageHelper;

	private BeanMapper mapper = new BeanMapper();

	@RequestMapping("role-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String roleName = ParamUtils.getString(parameterMap, "rName");

		List<AuthRoleDTO> roleDTOs = new ArrayList<AuthRoleDTO>();

		List<AuthRole> roles;
		if (StringUtils.isEmpty(roleName)) {
			roles = (List<AuthRole>) roleManager.find(" from AuthRole");
		} else {
			/* 字符串两端全模糊匹配 */
			roles = (List<AuthRole>) roleManager.findByLike("roleName", "%"
					+ roleName + "%");
		}

		for (AuthRole role : roles) {
			AuthRoleDTO dest = new AuthRoleDTO();
			mapper.copy(role, dest);
			dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(role
					.getStatFlag()));
			roleDTOs.add(dest);
		}

		page.setResult(roleDTOs);
		model.addAttribute("page", page);

		return "auth/role-list";
	}

	@RequestMapping("role-input")
	public String input(
			@RequestParam(value = "rid", required = false) Long rid, Model model) {

		if (rid != null) {

			AuthRole role = roleManager.load(rid);

			AuthRoleDTO dest = new AuthRoleDTO();

			mapper.copy(role, dest);
			dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(role
					.getStatFlag()));

			model.addAttribute("rid", rid);
			model.addAttribute("role", dest);
		}

		return "auth/role-input";
	}

	@RequestMapping("role-save")
	public String save(@ModelAttribute AuthRoleDTO roleDTO, Model model) {
		Long roleId = roleDTO.getId();
		String roleCode = roleDTO.getCode();
		String roleName = roleDTO.getName();
		String roleDesc = roleDTO.getDescn();
		String statFlag = roleDTO.getStatFlag();

		statFlag = statFlag == null ? CONSTANTS.STAT_FLAG_CLOSE : statFlag;

		if (roleId == null) {
			// Create an new role.

			AuthRole role = new AuthRole();
			role.setCode(roleCode);
			role.setName(roleName);
			role.setDescn(roleDesc);
			role.setStatFlag(statFlag);

			roleManager.save(role);

		} else {

			// Modify an exists role.

			AuthRole role = roleManager.load(roleId);
			role.setId(roleId);
			role.setCode(roleCode);
			role.setName(roleName);
			role.setDescn(roleDesc);
			role.setStatFlag(statFlag);

			roleManager.save(role);
		}

		return "redirect:role-list.do";
	}

	@RequestMapping("role-res-input")
	public String configResInput(
			@RequestParam(value = "rid", required = true) Long rid, Model model) {

		List<AppFunctionDTO> funcs = new ArrayList<AppFunctionDTO>();

		if (rid != null) {

			AuthRole role = roleManager.get(rid);
			AuthRoleDTO dest = new AuthRoleDTO();
			mapper.copy(role, dest);
			dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(role
					.getStatFlag()));

			funcs = authService.createFunctionListOnSelected(role);

			model.addAttribute("rid", rid);
			model.addAttribute("role", dest);
			model.addAttribute("funcs", funcs);

		}

		return "auth/role-res-input";
	}

	@RequestMapping("role-res-save")
	public String configResSave(
			@RequestParam(value = "rid", required = true) Long rid,
			@RequestParam(value = "funcIds", required = false) String funcIds,
			Model model) {

		log.debug(funcIds);

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

		return "redirect:role-list.do";
	}

}

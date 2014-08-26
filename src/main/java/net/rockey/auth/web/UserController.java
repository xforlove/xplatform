package net.rockey.auth.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.rockey.auth.manager.UserManager;
import net.rockey.auth.model.AuthRole;
import net.rockey.auth.model.AuthUser;
import net.rockey.auth.service.AuthService;
import net.rockey.auth.support.AuthRoleDTO;
import net.rockey.auth.support.AuthUserConverter;
import net.rockey.auth.support.AuthUserDTO;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.CPublic;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.StringUtils;
import net.rockey.ext.export.Exportor;
import net.rockey.ext.export.TableModel;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("auth")
public class UserController {

	private final Logger log = LogUtils.getLogger(UserController.class, true);

	@Autowired
	private UserManager userManager;

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthUserConverter userConverter;

	@Autowired
	private MessageHelper messageHelper;

	@Autowired
	private Exportor exportor;

	@RequestMapping("user-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String name = ParamUtils.getString(parameterMap, "name");

		/*
		 * page = userManager.pagedQuery(" from AuthUser ", page.getPageNo(),
		 * page.getPageSize());
		 * 
		 * List<AuthUser> users = (List<AuthUser>) page.getResult();
		 */

		List<AuthUserDTO> userDtos = new ArrayList<AuthUserDTO>();

		List<AuthUser> users;
		if (StringUtils.isEmpty(name)) {
			users = (List<AuthUser>) userManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			users = (List<AuthUser>) userManager.findByLike("name", "%" + name
					+ "%");
		}

		for (AuthUser user : users) {

			List<AuthRole> roles = authService.findRolesBy(user.getId());

			userDtos.add(userConverter.createAuthUserDto(user, roles));
		}

		page.setResult(userDtos);
		model.addAttribute("page", page);

		return "auth/user-list";
	}

	@RequestMapping("user-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {

		if (id != null) {

			AuthUser user = userManager.load(id);
			AuthUserDTO userDTO = userConverter.createAuthUserDto(user);

			model.addAttribute("model", userDTO);
		}

		return "auth/user-input";
	}

	@RequestMapping("user-save")
	public String save(@ModelAttribute AuthUserDTO userDTO,
			RedirectAttributes redirectAttributes, Model model) {
		Long userId = userDTO.getId();
		String loginId = userDTO.getLoginId();
		String userName = userDTO.getName();
		String statFlag = userDTO.getStatFlag();

		log.debug(statFlag);

		statFlag = statFlag == null ? CONSTANTS.STAT_FLAG_CLOSE : statFlag;

		AuthUser user = (userId == null) ? (new AuthUser()) : (userManager
				.get(userId));

		user.setId(userId);
		user.setName(userName);
		user.setLoginId(loginId);
		user.setLoginPass(CONSTANTS.USER_PASSWORD_DEFAULT);
		user.setStatFlag(statFlag);
		user.setEmail(CONSTANTS.USER_EMAIL_DEFAULT);
		user.setPhone(CONSTANTS.USER_PHONE_DEFAULT);

		userManager.save(user);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:user-list.do";
	}

	@RequestMapping("user-role-input")
	public String configRoleInput(
			@RequestParam(value = "uid", required = true) Long uid, Model model) {

		List<AuthRoleDTO> roles = new ArrayList<AuthRoleDTO>();

		if (uid != null) {

			AuthUser user = userManager.load(uid);
			AuthUserDTO userDTO = userConverter.createAuthUserDto(user);
			roles = authService.createRoleListOnSelected(user);

			model.addAttribute("uid", uid);
			model.addAttribute("user", userDTO);
			model.addAttribute("roles", roles);
		}

		return "auth/user-role-input";
	}

	@RequestMapping("user-role-save")
	public String configRoleSave(
			@RequestParam(value = "uid", required = true) Long uid,
			@RequestParam(value = "roleIds", required = false) String roleIds,
			RedirectAttributes redirectAttributes, Model model) {
		log.debug(roleIds);

		if (StringUtils.isEmpty(roleIds)) {
			authService.clearUserRoleBy(userManager.get(uid));
		} else {
			String[] roleArr = StringUtils.splitByWholeSeparator(roleIds, ",");
			List<Long> roleList = new ArrayList<Long>();
			for (int i = 0; i < roleArr.length; i++) {
				roleList.add(Long.parseLong(roleArr[i]));
			}
			authService.configUserRole(uid, roleList, true);
		}

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:user-list.do";
	}

	@RequestMapping("user-export")
	public void export(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap,
			HttpServletResponse response) throws Exception {

		String name = ParamUtils.getString(parameterMap, "name");

		List<AuthUserDTO> userDtos = new ArrayList<AuthUserDTO>();

		List<AuthUser> users;
		if (StringUtils.isEmpty(name)) {
			users = (List<AuthUser>) userManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			users = (List<AuthUser>) userManager.findByLike("name", "%" + name
					+ "%");
		}

		// 将查询结果导出
		TableModel tbModel = new TableModel();
		tbModel.setName("user");
		tbModel.addHeaders("id", "name", "statFlag");
		tbModel.setData(users);
		exportor.export(response, tbModel);
	}

	@RequestMapping("user-info")
	public String info(Model model) {
		Subject currentUser = SecurityUtils.getSubject();

		AuthUser user = userManager.findUnique(
				" from AuthUser where loginId = ?",
				CPublic.parseStr(currentUser.getPrincipal()));

		model.addAttribute("user", user);

		AuthUserDTO userDTO = userConverter.createAuthUserDto(user);

		model.addAttribute("uid", user.getId());
		model.addAttribute("user", userDTO);

		return "auth/user-info";
	}

}

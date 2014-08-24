package net.rockey.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.rockey.app.manager.FunctionManager;
import net.rockey.app.model.AppFunction;
import net.rockey.app.support.AppFunctionDTO;
import net.rockey.auth.manager.RoleManager;
import net.rockey.auth.manager.UserManager;
import net.rockey.auth.model.AuthRole;
import net.rockey.auth.model.AuthUser;
import net.rockey.auth.support.AuthRoleDTO;
import net.rockey.core.mapper.BeanMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
@Service
public class AuthService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserManager userManager;

	@Autowired
	private RoleManager roleManager;

	@Autowired
	private FunctionManager functionManager;

	BeanMapper mapper = new BeanMapper();

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<AuthRole> findRoles() {
		return roleManager
				.find(" from AuthRole where statFlag = 'Normal' order by id ");
	}

	/**
	 * 查询用户对应的角色集合
	 * 
	 * @param user
	 * @return
	 */
	// @Transactional(propagation = Propagation.NOT_SUPPORTED)
	// public List<Map<String, Object>> findRolesBy(Long uid) {
	// StringBuffer sql = new StringBuffer(128);
	// sql.append(" select r.id as id, r.code as code, r.name as name");
	// sql.append(" from auth_user_role as ur, auth_role as r ");
	// sql.append(" where ur.role_id = r.id and ur.user_id = ? ");
	//
	// return jdbcTemplate.queryForList(sql.toString(), uid);
	// }

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<AuthRole> findRolesBy(Long uid) {
		AuthUser user = userManager.load(uid);

		Assert.notNull(user);
		
		return user.getRoles();
	}

	/**
	 * 查询用户对应的资源集合
	 * 
	 * @param user
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Map<String, Object>> findFunctionsBy(Long uid) {
		StringBuffer sql = new StringBuffer(128);
		sql.append(" select rf.function_id as id, f.code as code ");
		sql.append(" from auth_role_function as rf, auth_user_role as ur, app_function f ");
		sql.append(" where rf.role_id = ur.role_id and f.id = rf.function_id and ur.user_id = ? ");

		return jdbcTemplate.queryForList(sql.toString(), uid);
	}

	public void configUserRole(Long userId, List<Long> roleIds,
			boolean clearFlag) {

		AuthUser user = userManager.get(userId);

		if (user == null) {
			// TODO
			return;
		}

		if (clearFlag) {
			clearUserRoleBy(user);
		}

		for (Long roleId : roleIds) {
			AuthRole role = roleManager.load(roleId);

			if (role == null) {
				// TODO
				continue;
			}

			user.getRoles().add(role);
		}
		userManager.save(user);

	}

	public void configRoleFunction(Long roleId, List<Long> functionIds,
			boolean clearFlag) {
		AuthRole role = roleManager.get(roleId);

		if (role == null) {
			// TODO
			return;
		}

		if (clearFlag) {
			clearRoleFunctionBy(role);
		}

		for (Long fid : functionIds) {
			AppFunction function = functionManager.load(fid);

			if (function == null) {
				// TODO
				continue;
			}

			role.getFunctions().add(function);

			roleManager.save(role);
		}

	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<AuthRoleDTO> createRoleListOnSelected(AuthUser user) {
		List<AuthRoleDTO> result = new ArrayList<AuthRoleDTO>();

		for (AuthRole role : findRoles()) {
			AuthRoleDTO dest = new AuthRoleDTO();
			mapper.copy(role, dest);
			dest.setSelected(matchUserRole(user, role));
			result.add(dest);
		}

		return result;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<AppFunctionDTO> createFunctionListOnSelected(AuthRole role) {
		List<AppFunctionDTO> result = new ArrayList<AppFunctionDTO>();

		for (AppFunction function : findFunctions()) {
			AppFunctionDTO dest = new AppFunctionDTO();
			mapper.copy(function, dest);
			dest.setSelected(matchRoleFunction(role, function));
			result.add(dest);
		}

		return result;
	}

	public List<AppFunction> findFunctions() {
		return functionManager
				.find(" from AppFunction where statFlag = 'Normal' order by id ");
	}

	/**
	 * 匹配用户与角色的对照关系
	 * 
	 * @param user
	 *            用户
	 * @param role
	 *            角色
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean matchUserRole(AuthUser user, AuthRole role) {
		Map<String, Object> result = jdbcTemplate
				.queryForMap(
						" select count(1) as cnt from auth_user_role ur where ur.user_id = ? and ur.role_id = ?",
						user.getId(), role.getId());

		Long cnt = (Long) result.get("cnt");
		return cnt > 0;
	}

	/**
	 * 匹配角色与功能的对照关系
	 * 
	 * @param role
	 *            角色
	 * @param function
	 *            功能
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean matchRoleFunction(AuthRole role, AppFunction function) {
		Map<String, Object> result = jdbcTemplate
				.queryForMap(
						" select count(1) as cnt from auth_role_function rf where rf.role_id = ? and rf.function_id = ?",
						role.getId(), function.getId());

		Long cnt = (Long) result.get("cnt");
		return cnt > 0;
	}

	/**
	 * 删除指定用户ID下的所有角色配置
	 * 
	 * @param userId
	 *            用户ID
	 */
	public void clearUserRoleBy(AuthUser user) {
		user.getRoles().clear();
		userManager.save(user);
	}

	/**
	 * 删除指定角色ID下的所有功能配置
	 * 
	 * @param roleId
	 *            角色ID
	 */
	public void clearRoleFunctionBy(AuthRole role) {
		role.getFunctions().clear();
		roleManager.save(role);
	}

}

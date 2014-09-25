package net.rockey.core.security;

import java.util.Map;

import net.rockey.system.auth.manager.UserManager;
import net.rockey.system.auth.model.AuthRole;
import net.rockey.system.auth.model.AuthUser;
import net.rockey.system.auth.service.AuthService;
import net.rockey.system.auth.support.AuthUserConverter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroDatabaseRealm extends AuthorizingRealm {

	@Autowired
	private UserManager userManager;

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthUserConverter authUserConverter;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		AuthUser user = userManager.findUniqueBy("loginId", token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getLoginId(),
					user.getLoginPass(), getName());
		} else {
			return null;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}
		String loginId = (String) getAvailablePrincipal(principals);
		AuthUser user = userManager.findUniqueBy("loginId", loginId);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			for (AuthRole role : authService.findRolesBy(user.getId())) {
				info.addRole(role.getCode());
			}

			for (Map<String, Object> item : authService.findFunctionsBy(user
					.getId())) {
				info.addStringPermission(String.valueOf(item.get("code")));
			}

			return info;
		} else {
			return null;
		}
	}

}

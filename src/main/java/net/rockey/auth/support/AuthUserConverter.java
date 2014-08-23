package net.rockey.auth.support;

import java.util.List;

import net.rockey.app.model.AppFunction;
import net.rockey.auth.model.AuthRole;
import net.rockey.auth.model.AuthUser;
import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.util.ViewTransfer;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AuthUserConverter {

	private BeanMapper mapper = new BeanMapper();

	/**
	 * 将PO转换成DTO
	 * 
	 * @param authUser
	 *            PO对象
	 * @return DTO对象
	 */
	public AuthUserDTO createAuthUserDto(AuthUser user) {
		return createAuthUserDto(user, null, null);
	}

	public AuthUserDTO createAuthUserDto(AuthUser user, List<AuthRole> roles) {
		return createAuthUserDto(user, roles, null);
	}

	public AuthUserDTO createAuthUserDto(AuthUser user, List<AuthRole> roles,
			List<AppFunction> functions) {

		Assert.notNull(user);

		AuthUserDTO dest = new AuthUserDTO();

		mapper.copy(user, dest);

		dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(user.getStatFlag()));

		if (roles != null && roles.size() > 0) {
			StringBuffer roleCodes = new StringBuffer();
			StringBuffer roleNames = new StringBuffer();

			for (AuthRole role : roles) {
				roleCodes.append(role.getCode()).append(",");
				roleNames.append(role.getName()).append(",");
			}

			if (roleCodes.length() > 0) {
				roleCodes.deleteCharAt(roleCodes.length() - 1);
			}

			if (roleNames.length() > 0) {
				roleNames.deleteCharAt(roleNames.length() - 1);
			}

			dest.setRoleCodes(roleCodes.toString());
			dest.setRoleNames(roleNames.toString());
		}

		if (functions != null && functions.size() > 0) {
			StringBuffer functionCodes = new StringBuffer();

			for (AppFunction function : functions) {
				functionCodes.append(function.getCode()).append(",");
			}

			if (functionCodes.length() > 0) {
				functionCodes.deleteCharAt(functionCodes.length() - 1);
			}

			dest.setPermissionCodes(functionCodes.toString());
		}

		return dest;
	}

}

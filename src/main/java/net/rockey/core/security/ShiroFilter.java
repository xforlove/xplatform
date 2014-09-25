package net.rockey.core.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rockey.system.auth.manager.UserManager;
import net.rockey.system.auth.model.AuthUser;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author ruoqi.du
 * 
 *         2014-7-10
 * 
 */
public class ShiroFilter implements Filter {

	@Autowired
	private UserManager userManager;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Principal principal = httpRequest.getUserPrincipal();
		if (principal != null) {
			Subject subject = SecurityUtils.getSubject();
			AuthUser user = userManager.findUniqueBy("loginId",
					principal.getName());
			if (user != null) {
				UsernamePasswordToken token = new UsernamePasswordToken(
						user.getLoginId(), user.getLoginPass());
				subject.login(token);
			} else {
				if (subject != null) {
					subject.logout();
				}
			}
		}
		chain.doFilter(httpRequest, httpResponse);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

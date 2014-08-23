package net.rockey.auth.web;

import javax.servlet.http.HttpServletRequest;

import net.rockey.auth.manager.UserManager;
import net.rockey.auth.model.AuthUser;
import net.rockey.core.util.LogUtils;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private static final Logger log = LogUtils.getLogger(LoginController.class,
			true);

	@Autowired
	private UserManager userManager;

	@RequestMapping("/login")
	public String login(@RequestParam("loginId") String loginId,
			@RequestParam("loginPass") String loginPass) throws Exception {

		log.debug(loginId + ", " + loginPass);

		AuthUser user = userManager.findUniqueBy("loginId", loginId);
		if (user != null) {
			log.debug(loginPass + ", " + user.getLoginPass());

			Subject currentUser = SecurityUtils.getSubject();
			if (loginPass.equals(user.getLoginPass())) {
				UsernamePasswordToken token = new UsernamePasswordToken(
						loginId, loginPass);
				token.setRememberMe(true);
				currentUser.login(token);
				return "redirect:/dashboard/dashboard.do";
			} else {

				log.debug("goto noPass.jsp");

				return "redirect:/common/noPass.jsp";
			}
		} else {
			return "redirect:/common/noAuth.jsp";
		}
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request) throws Exception {
		Subject subject = SecurityUtils.getSubject();

		if (subject != null) {
			subject.logout();
		}

		request.getSession().invalidate();
	}
}

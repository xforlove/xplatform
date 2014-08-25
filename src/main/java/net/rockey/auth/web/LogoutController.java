package net.rockey.auth.web;

import javax.servlet.http.HttpServletRequest;

import net.rockey.core.util.LogUtils;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	private final Logger log = LogUtils.getLogger(LogoutController.class, true);

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws Exception {
		Subject subject = SecurityUtils.getSubject();

		if (subject != null) {
			subject.logout();
		}

		request.getSession().invalidate();

		return "redirect:/index.jsp";
	}
}

package net.rockey.system.auth.web;

import javax.servlet.http.HttpServletRequest;

import net.rockey.core.util.ShiroUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	private final static Logger log = LoggerFactory
			.getLogger(LogoutController.class);

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		String loginId = (String) ShiroUtils.getAttribute("login_id");

		if (subject != null) {
			subject.logout();
		}

		request.getSession().invalidate();

		log.info("{} is log out.", loginId);
		
		return "redirect:/index.jsp";
	}
}

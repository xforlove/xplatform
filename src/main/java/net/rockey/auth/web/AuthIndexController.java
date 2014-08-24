package net.rockey.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthIndexController {

	@RequestMapping("index")
	public String index(Model model) {
		return "auth/index";
	}
}

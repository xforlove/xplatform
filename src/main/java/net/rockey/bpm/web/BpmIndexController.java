package net.rockey.bpm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bpm")
public class BpmIndexController {

	@RequestMapping("index")
	public String index(Model model) {
		return "bpm/index";
	}
}

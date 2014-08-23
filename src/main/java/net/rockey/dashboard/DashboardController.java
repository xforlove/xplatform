package net.rockey.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	@RequestMapping("/dashboard")
	public String dashboard() throws Exception {

		// TODO

		return "dashboard/dashboard";
	}
}

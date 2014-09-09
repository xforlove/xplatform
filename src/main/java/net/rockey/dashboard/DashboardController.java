package net.rockey.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	private static final Logger log = LoggerFactory
			.getLogger(DashboardController.class);
	
	@RequestMapping("/dashboard")
	public String dashboard() throws Exception {

		// TODO

		return "dashboard/dashboard";
	}
}

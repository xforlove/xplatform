package net.rockey.dashboard;

import net.rockey.core.util.LogUtils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	private final Logger log = LogUtils.getLogger(DashboardController.class, true);
	
	@RequestMapping("/dashboard")
	public String dashboard() throws Exception {

		// TODO

		return "dashboard/dashboard";
	}
}

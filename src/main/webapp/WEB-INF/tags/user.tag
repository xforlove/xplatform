<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@tag import="org.springframework.context.ApplicationContext"%>
<%@tag
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="net.rockey.auth.manager.UserManager"%>
<%
	Object userId = jspContext.getAttribute("userId");
	if (userId == null) {
		out.print("");
	} else {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(application);
		UserManager userManager = ctx.getBean(UserManager.class);
		try {
			out.print(userManager
					.get(Long.parseLong(userId.toString())).getName());
		} catch (Exception ex) {
			System.out.println(userId);
		}
	}
%>
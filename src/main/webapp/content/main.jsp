<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="/common/meta.jsp"%>
<title>首页</title>
<%@include file="/common/s.jsp"%>
</head>

<body>
	<div>
		<shiro:user>
			Hello, <shiro:principal />, how are you today? 
		</shiro:user>
		<shiro:hasPermission name="user:create">
			you have permission ( user:create ).
		</shiro:hasPermission>
	</div>
</body>
</html>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentNavi", "index");
%>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
<%@include file="/common/meta.jsp"%>
<title><%=net.rockey.core.util.PropertyUtils.getInstance()
					.getValue("project_name")%></title>
<%@include file="/common/s.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		// TODO

	});
</script>
</head>

<body>

	<%@include file="/header/bpm.jsp"%>

	<div class="container-fluid">

		<div class="row">

			<%@include file="/navigation/auth.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li class="active"><a href="javascript: void(0);">流程管理</a></li>
				</ol>

				<div>欢迎使用流程管理。</div>
			</div>

		</div>

	</div>
	<!-- /.container-fluid -->

</body>
</html>
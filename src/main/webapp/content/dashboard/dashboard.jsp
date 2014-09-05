<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
pageContext.setAttribute("currentHeader", "dashboard");
%>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
<%@include file="/common/meta.jsp"%>
<title>用户列表</title>
<%@include file="/common/s.jsp"%>

<style type="text/css">

/* Move down content because we have a fixed navbar that is 50px tall */
body {
	padding-top: 60px;
}

.msg-box{
	
}

</style>
</head>

<body>

	<%@include file="/header/dashboard.jsp"%>

	<div class="container-fluid">

		<div class="col-sm-6 col-md-6 msg-box">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">待办任务
						<button type="button" class="close">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
	
						<table class="table table-striped">
							<thead>
								<tr>
									<td>序号</td>
									<td>用户ID</td>
									<td>用户名</td>
									<td>状态</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>10001</td>
									<td>ruoqi.du</td>
									<td>启用</td>
								</tr>
								<tr>
									<td>2</td>
									<td>10002</td>
									<td>ruoqi.du</td>
									<td>禁用</td>
								</tr>
							</tbody>
						</table>
	
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-sm-6 col-md-6 msg-box">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">待办任务
						<button type="button" class="close">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
	
						<table class="table table-striped">
							<thead>
								<tr>
									<td>序号</td>
									<td>用户ID</td>
									<td>用户名</td>
									<td>状态</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>10001</td>
									<td>ruoqi.du</td>
									<td>启用</td>
								</tr>
								<tr>
									<td>2</td>
									<td>10002</td>
									<td>ruoqi.du</td>
									<td>禁用</td>
								</tr>
							</tbody>
						</table>
	
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>

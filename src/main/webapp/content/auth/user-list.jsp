<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentNavi", "user");%>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
<%@include file="/common/meta.jsp"%>
<title><%=net.rockey.core.util.PropertyUtils.getInstance().getValue("project_name") %></title>
<%@include file="/common/s.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		// TODO
		
	});
	
	
	function userExport() {
		document.searchForm.action = 'user-export.do';
		document.searchForm.submit();
		return true;
	}
</script>

</head>

<body>

	<%@include file="/header/auth.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/auth.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="ROCK_DT_SEARCH">
					<form id="searchForm" name="searchForm" class="form-inline" role="form" action="user-list.do" method="post">
						
						<div class="form-group">
							<label for="name">用户名：</label>
							<input type="text" class="form-control" id="uName" name="uName" value="${param.uName }">
						</div>
						
						<div class="btn-group">
							<button type="button" class="btn btn-default" onclick="javascript: document.searchForm.submit();">查询</button>
							
							<shiro:hasPermission name="user:create">							
							<button type="button" class="btn btn-default" onclick="javascript: location.href='user-input.do'">创建</button>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="user:export">
							<button type="button" class="btn btn-default" onclick="javascript: return userExport();">导出</button>
							</shiro:hasPermission>
						</div>
					</form>
				</div>
				
				<div class="table-responsive">
					<table class="table table-striped" id="ROCK_DT">
						<thead>
							<tr>
								<td>序号</td>
								<td>用户名</td>
								<td>状态</td>
								<td>&nbsp;</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.result }" var="object" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${object.name }</td>
									<td>${object.statFlagCn }</td>
									<td>
										<div class="btn-group">
											<shiro:hasPermission name="user:edit">
											<button type="button" class="btn btn-default btn-sm" 
												onclick="javascript: location.href='user-input.do?uid=${object.id }' ">编辑</button>
											</shiro:hasPermission>
											
											<shiro:hasPermission name="user:configRole">
											<button type="button" class="btn btn-default btn-sm" 
												onclick="javascript: location.href='user-role-input.do?uid=${object.id }' ">设定角色</button>
											</shiro:hasPermission>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>
	<!-- /.container-fluid -->
	
</body>
</html>
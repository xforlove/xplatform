<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<% 
pageContext.setAttribute("currentHeader", "workspace");
pageContext.setAttribute("currentNavi", "task-todo"); 
%>

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
	
</script>

</head>

<body>

	<%@include file="/header/workspace.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/workspace.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="ROCK_DT_SEARCH">
					<form id="searchForm" name="searchForm" class="form-inline" role="form" action="workspace-listPersonalTasks.do" method="post">
						
						<div class="form-group">
							<label for="name">流程名称：</label>
							<input type="text" class="form-control" id="name" name="name" value="${param.name }">
						</div>
						
						<div class="btn-group">
							<button type="button" class="btn btn-default" onclick="javascript: document.searchForm.submit();">
								<i class="glyphicon glyphicon-search"> 查询</i>
							</button>
							<button type="button" class="btn btn-default" onclick="javascript: location.href='process-input.do'">
								<i class="glyphicon glyphicon-plus"> 创建</i>
							</button>
							<button type="button" class="btn btn-default" onclick="javascript: void(0);">
								<i class="glyphicon glyphicon-export"> 导出</i>
							</button>
						</div>
					</form>
				</div>
				
				<div class="table-responsive">
					<table class="table table-striped" id="ROCK_DT">
						<thead>
							<tr>
								<td width="50">序号</td>
								<td>名称</td>
								<td>创建时间</td>
								<td>负责人</td>
								<td>状态</td>
								<td width="200">&nbsp;</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${tasks }" var="object" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${object.name }</td>
									<td><fmt:formatDate value="${object.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${object.assignee}</td>
									<td>${object.suspended ? '挂起' : '激活'}</td>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm" 
												onclick="javascript: location.href='../form/form-viewTaskForm.do?taskId=${object.id }' ">
												<i class="glyphicon glyphicon-edit"> 处理</i>
											</button>
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
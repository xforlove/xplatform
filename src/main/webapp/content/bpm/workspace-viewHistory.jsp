<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<% pageContext.setAttribute("currentNavi", "task-done"); %>

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
				
					<img src="workspace-graphHistoryProcessInstance.do?processInstanceId=${param.processInstanceId}">
					
				</div>
				
				<div class="table-responsive">
					<table class="table table-striped" id="ROCK_DT">
						<thead>
							<tr>
								<td width="50">序号</td>
								<td>名称</td>
								<td>开始时间</td>
								<td>结束时间</td>
								<td>负责人</td>
								<td>处理结果</td>
								<td width="200">&nbsp;</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${historicTasks }" var="object" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${object.name }</td>
									<td><fmt:formatDate value="${object.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${object.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${object.assignee}</td>
									<td>${object.deleteReason}</td>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm" 
												onclick="javascript: location.href='workspace-viewHistory.do?processInstanceId=${object.processInstanceId}' ">
												<i class="glyphicon glyphicon-edit"> 查看</i>
											</button>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<div class="table-responsive">
					<table class="table table-striped" id="ROCK_SDT">
						<thead>
							<tr>
								<td>名称</td>
								<td>值</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${historicVariableInstances }" var="object" varStatus="status">
								<tr>
									<td>${object.variableName }</td>
									<td>${object.value}</td>
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
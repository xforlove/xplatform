<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<% pageContext.setAttribute("currentNavi", "bpm-processInstance"); %>

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

	<%@include file="/header/bpm-console.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/bpm-console.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="table-responsive">
					<table class="table table-striped" id="ROCK_DT">
						<thead>
							<tr>
								<td width="50">序号</td>
								<td>流程定义</td>
								<td>环节</td>
								<td>状态</td>
								<td width="200">&nbsp;</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${processInstances }" var="object" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${object.processDefinitionId }</td>
									<td>${object.activityId}</td>
									<td>
										<c:if test="${object.suspended }">
											挂起
										</c:if>
										<c:if test="${not object.suspended }">
											激活
										</c:if>
									</td>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm" 
												onclick="javascript: location.href='console-viewHistory.do?processInstanceId=${object.id}' ">
												<i class="glyphicon glyphicon-file"> 历史</i>
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
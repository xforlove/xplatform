<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<% pageContext.setAttribute("currentNavi", "bpm-process"); %>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
<%@include file="/common/meta.jsp"%>
<title><%=net.rockey.core.util.PropertyUtils.getInstance().getValue("project_name")%></title>
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
					<table class="table table-striped" id="ROCK_SDT">
						<thead>
							<tr>
								<th width="50">序号</th>
								<th>类型</th>
								<th>节点</th>
								<th>人员</th>
								<th>事件</th>
								<th>规则</th>
								<th>表单</th>
								<th>操作</th>
								<th>提醒</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bpmConfNodes }" var="object" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${object.type }</td>
									<td>${object.name }</td>
									<td>
										<c:if test="${object.confUser == 0 }">
											<button type="button" class="btn btn-default btn-sm" onclick="javascript: location.href='bpm-conf-user-list.do?bpmConfNodeId=${object.id}' ">
												<i class="glyphicon glyphicon-edit"></i>
											</button>
										</c:if>
										<c:if test="${object.confUser == 1 }">
											<button type="button" class="btn btn-primary btn-sm" onclick="javascript: location.href='bpm-conf-user-list.do?bpmConfNodeId=${object.id}' ">
												<i class="glyphicon glyphicon-edit"></i>
											</button>
										</c:if>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
										<c:if test="${object.confForm == 0 }">
											<button type="button" class="btn btn-default btn-sm" onclick="javascript: location.href='bpm-conf-form-list.do?bpmConfNodeId=${object.id}' ">
												<i class="glyphicon glyphicon-edit"></i>
											</button>
										</c:if>
										<c:if test="${object.confForm == 1 }">
											<button type="button" class="btn btn-primary btn-sm" onclick="javascript: location.href='bpm-conf-form-list.do?bpmConfNodeId=${object.id}' ">
												<i class="glyphicon glyphicon-edit"></i>
											</button>
										</c:if>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
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
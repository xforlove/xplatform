<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
pageContext.setAttribute("currentHeader", "param");
pageContext.setAttribute("currentNavi", "param");
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

	<%@include file="/header/param.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/param.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="ROCK_DT_SEARCH">
					<form id="searchForm" name="searchForm" class="form-inline" role="form" action="/${scopeUrl }/param/param-list.do" method="post">
						
						<div class="form-group">
							<label>参数名称：</label>
							<input type="text" class="form-control" id="name" name="name" value="${param.name }">
						</div>
						
						<div class="btn-group">
							<button type="button" class="btn btn-default" onclick="javascript: document.searchForm.submit();">
								<i class="glyphicon glyphicon-search"> 查询</i>
							</button>
							<button type="button" class="btn btn-default" onclick="javascript: location.href='param-input.do'">
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
								<th width="50">序号</th>
								<th>代号</th>
								<th>名称</th>
								<th>类型</th>
								<th>参数值</th>
								<th>默认值</th>
								<th>描述</th>
								<th width="200">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${params }" var="object" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${object.code }</td>
									<td>${object.name }</td>
									<td>
										<c:if test="${object.type == 1}">布尔型</c:if>
										<c:if test="${object.type == 2}">整型</c:if>
										<c:if test="${object.type == 3}">双精度浮点型</c:if>
										<c:if test="${object.type == 4}">字符串型</c:if>
									</td>
									<td>${object.value }</td>
									<td>${object.defValue }</td>
									<td>${object.descn }</td>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm" 
												onclick="javascript: location.href='param-input.do?id=${object.id }' ">
												<i class="glyphicon glyphicon-edit"> 编辑</i>	
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
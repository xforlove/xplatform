<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentNavi", "bpm-category");%>

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

	<%@include file="/header/bpm.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/auth.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="ROCK_DT_SEARCH">
					<form id="searchForm" name="searchForm" class="form-inline" role="form" action="user-list.do" method="post">
						
						<div class="form-group">
							<label for="name">流程分类：</label>
							<input type="text" class="form-control" id="name" name="name" value="${param.name }">
						</div>
						
						<div class="btn-group">
							<button type="button" class="btn btn-default" onclick="javascript: document.searchForm.submit();">查询</button>
							<button type="button" class="btn btn-default" onclick="javascript: location.href='bpm-category-input.do'">创建</button>
							<button type="button" class="btn btn-default" onclick="javascript: void(0);">导出</button>
						</div>
					</form>
				</div>
				
				<div class="table-responsive">
					<table class="table table-striped" id="ROCK_DT">
						<thead>
							<tr>
								<td>序号</td>
								<td>分类名称</td>
								<td>排序</td>
								<td>&nbsp;</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.result }" var="object" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${object.name }</td>
									<td>${object.priority }</td>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm" 
												onclick="javascript: location.href='bpm-category-input.do?id=${object.id }' ">编辑</button>
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
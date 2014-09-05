<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<% 
pageContext.setAttribute("currentHeader", "bpm-console");
pageContext.setAttribute("currentNavi", "bpm-process"); 
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

	<%@include file="/header/bpm-console.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/bpm-console.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="ROCK_DT_SEARCH">
					<form id="searchForm" name="searchForm" class="form-inline" role="form" action="bpm-conf-form-list.do" method="post">
						
						<div class="form-group">
							<label for="name">流程分类：</label>
							<input type="text" class="form-control" id="name" name="name" value="${param.name }">
						</div>
						
						<div class="btn-group">
							<button type="button" class="btn btn-default" onclick="javascript: document.searchForm.submit();">
								<i class="glyphicon glyphicon-search"> 查询</i>
							</button>
							<button type="button" class="btn btn-default" onclick="javascript: location.href='bpm-category-input.do'">
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
								<td>类型</td>
								<td width="200">&nbsp;</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bpmConfForms }" var="object" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${object.value }</td>
									<td>${object.type == 0 ? '电子表单' : '外部表单' }</td>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm" 
												onclick="javascript: location.href='bpm-conf-form-remove.do?id=${object.id }' ">
												<i class="glyphicon glyphicon-trash"> 删除</i>	
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
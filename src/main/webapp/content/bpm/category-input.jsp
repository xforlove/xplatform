<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentNavi", "category");%>

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
			
			<%@include file="/navigation/bpm.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="javascript: void(0);">流程管理</a></li>
					<li><a href="javascript: location.href='category-list.do'">流程分类</a></li>
					<li class="active">编辑流程分类</li>
				</ol>

				<form class="form-horizontal" action="/${scopeUrl }/bpm/category-save.do" method="POST" role="form">
					<c:if test="${model != null}">
						<input type="hidden" id="id" name="id" value="${model.id}">
					</c:if>
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">分类名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="name" name="name" value="${model.name }">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4">
							<button type="submit" class="btn btn-primary">提交</button>
							<button type="button" class="btn btn-default" onclick="javascript: history.back();">取消</button>
						</div>
					</div>
				</form>
				
			</div>

		</div>

	</div>
	<!-- /.container-fluid -->
	
</body>
</html>
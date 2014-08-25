<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentNavi", "role");%>

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

	<%@include file="/header/auth.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/auth.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="javascript: void(0);">权限体系</a></li>
					<li><a href="javascript: location.href='role-list.do'">角色列表</a></li>
					<li class="active">权限分配</li>
				</ol>
				
				<form class="form-horizontal" action="/${scopeUrl }/auth/role-res-save.do" method="POST" role="form">
					<input type="hidden" id="rid" name="rid" value="${role.id }" >
					
					<div class="form-group">
						<label for="code" class="col-sm-2 control-label">角色代号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="code" name="code" value="${role.code }" disabled="disabled">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">角色名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="name" name="name" value="${role.name }" disabled="disabled">
						</div>
					</div>
					<div class="form-group">
						<label for="roleIds" class="col-sm-2 control-label">功能</label>
						<div class="col-sm-4">
							<select id="funcIds" name="funcIds" class="multiselect" multiple="multiple" >
								<c:forEach items="${funcs }" var="object" varStatus="status">
									<option value="${object.id }" ${object.selected ? 'selected' : ' '}>${object.name }/${object.code }</option>
								</c:forEach>
				            </select>
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
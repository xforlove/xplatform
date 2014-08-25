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

		initChkbox('statFlag', 'NORMAL');

		$('#statFlag').click(function(){
			chkboxOnclick('statFlag', 'NORMAL', 'CLOSE');
		});

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
					<li><a href="javascript: void(0);">权限体系</a></li>
					<li><a href="javascript: location.href='role-list.do'">角色列表</a></li>
					<li class="active">编辑角色</li>
				</ol>

				<form class="form-horizontal" action="/${scopeUrl }/auth/role-save.do" method="POST" role="form">
					<c:if test="${id != null}">
						<input type="hidden" id="id" name="id" value="${role.id}">
					</c:if>
					
					<div class="form-group">
						<label for="code" class="col-sm-2 control-label">角色代号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="code" name="code" value="${role.code }">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">角色名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="name" name="name" value="${role.name }">
						</div>
					</div>
					<div class="form-group">
						<label for="descn" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-4">
							<textarea rows="3" class="form-control" id="descn" name="descn">${role.descn }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox col-sm-offset-2 col-sm-4">
							<label>
								<input type="checkbox" id="statFlag" name="statFlag" value="${role.statFlag }"> 启用
							</label>
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
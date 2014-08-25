<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentNavi", "function");%>

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

	<%@include file="/header/auth.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/auth.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="javascript: void(0);">权限体系</a></li>
					<li><a href="javascript: location.href='function-list.do'">功能列表</a></li>
					<li class="active">编辑功能</li>
				</ol>

				<form class="form-horizontal" action="/${scopeUrl }/app/function-save.do" method="POST" role="form">
					<c:if test="${id != null}">
						<input type="hidden" id="id" name="id" value="${func.id}">
					</c:if>
					
					<div class="form-group">
						<label for="code" class="col-sm-2 control-label">代号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="code" name="code" value="${func.code }">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">功能名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="name" name="name" value="${func.name }">
						</div>
					</div>
					<div class="form-group">
						<label for="action" class="col-sm-2 control-label">动作</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="action" name="action" value="${func.action }">
						</div>
					</div>
					<div class="form-group">
						<label for="groupId" class="col-sm-2 control-label">功能组</label>
						<div class="col-sm-4">
							<select id="groupId" name="groupId" class="singleselect" multiple="multiple">
								<c:forEach items="${funcGrps }" var="object" varStatus="status">
									<option value="${object.id }" ${object.selected ? 'selected' : ' '}>${object.name }</option>
								</c:forEach>
				            </select>
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox col-sm-offset-2 col-sm-4">
							<label>
								<input type="checkbox" id="statFlag" name="statFlag" value="${func.statFlag }"> 启用
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
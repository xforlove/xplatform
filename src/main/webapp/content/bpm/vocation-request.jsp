<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentNavi", "process");
%>

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

	<%@include file="/header/workspace.jsp"%>

	<div class="container-fluid">

		<div class="row">

			<%@include file="/navigation/workspace.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="javascript: void(0);">个人事务</a></li>
					<li><a href="javascript: location.href='workspace-home.do'">发起流程</a></li>
					<li class="active">请假申请</li>
				</ol>

				<form class="form-horizontal" action="/${scopeUrl }/form/form-startProcessInstance.do" method="POST" role="form">
					<input type="hidden" id="processDefinitionId" name="processDefinitionId" value="${param.processDefinitionId}">
					<input type="hidden" id="bpmProcessId" name="bpmProcessId" value="${param.bpmProcessId}">
					<input type="hidden" id="autoCompleteFirstTask" name="autoCompleteFirstTask" value="${param.autoCompleteFirstTask}">
					<input type="hidden" id="businessKey" name="businessKey" value="${param.businessKey}">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">请假类型</label>
						<div class="col-sm-4">
							<select id="type" name="type" class="singleselect"
								multiple="multiple">
								<option value="1">年假</option>
								<option value="2">事假</option>
								<option value="3">病假</option>
								<option value="4">婚假</option>
								<option value="5">调休</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">请假天数</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="days" name="days" >
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">请假原因</label>
						<div class="col-sm-4">
							<textarea rows="3" class="form-control" id="descn" name="descn"></textarea>
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
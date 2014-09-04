<%@page import="org.apache.shiro.SecurityUtils"%>
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
		
		loadLeaders();
	});
	
	function loadLeaders(){
		
		$.ajax({
			type : "GET",
			url : "../auth/ajax-users.do",
			async: true,
			success : function(data) {
				$("#leader").html(data);
			},
			error : function(xmlHttpRequest, errorMessage, exception) {
				alert(errorMessage);
			}
		});

	}
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
					<!-- 流程数据 - Start -->
					<input type="hidden" id="processDefinitionId" name="processDefinitionId" value="${processDefinitionId}">
					<input type="hidden" id="bpmProcessId" name="bpmProcessId" value="${bpmProcessId}">
					<input type="hidden" id="autoCompleteFirstTask" name="autoCompleteFirstTask" value="${autoCompleteFirstTask}">
					<input type="hidden" id="businessKey" name="businessKey" value="${businessKey}">
					<input type="hidden" id="creator" name="creator" value="${model.creator}">
					<!-- 流程数据 - End -->

					<div class="form-group">
						<label for="type" class="col-sm-2 control-label">请假类型</label>
						<div class="col-sm-4">
							<select class="form-control" id="type" name="type">
								<option value="ONSELECT">请选择</option>
								<option value="1">年假</option>
								<option value="2">事假</option>
								<option value="3">病假</option>
								<option value="4">婚假</option>
								<option value="5">调休</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="applyDuration" class="col-sm-2 control-label">请假天数</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="applyDuration" name="pp_applyDuration" >
						</div>
					</div>
					<div class="form-group">
						<label for="reason" class="col-sm-2 control-label">请假原因</label>
						<div class="col-sm-4">
							<textarea rows="3" class="form-control" id="reason" name="reason"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="leader" class="col-sm-2 control-label">指定审核人</label>
						<div class="col-sm-4">
							<select class="form-control" id="leader" name="pp_leader"></select>
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
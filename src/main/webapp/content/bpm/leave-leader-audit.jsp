<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<% pageContext.setAttribute("currentNavi", "task-todo"); %>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
<%@include file="/common/meta.jsp"%>
<title><%=net.rockey.core.util.PropertyUtils.getInstance().getValue("project_name")%></title>
<%@include file="/common/s.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		// TODO
		$("#auditPassDiv").hide();
		$("#auditRejectDiv").hide();
	});
	
	function loadRecorders(){
		$.ajax({
			type : "GET",
			url : "../auth/ajax-users.do",
			async: true,
			success : function(data) {
				$("#recorder").html(data);
			},
			error : function(xmlHttpRequest, errorMessage, exception) {
				alert(errorMessage);
			}
		});
	}
	
	function changeLeaderAudit(value){
		if (value == 1) {
			// 同意
			loadRecorders();
			$("#auditPassDiv").show();
		} else if (value == 0) {
			// 驳回
			$("#auditRejectDiv").show();
		} else {
			alert("invalidate value : " + value);
		}
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
					<li><a href="javascript: location.href='workspace-home.do'">待办任务</a></li>
					<li class="active">请假审批</li>
				</ol>

				<form id="businessForm" class="form-horizontal" action="/${scopeUrl }/form/form-completeTask.do" method="POST" role="form">

					<!-- 流程数据 - Start -->
					<input type="hidden" id="taskId" name="taskId" value="${taskId}">
					<input type="hidden" id="businessKey" name="businessKey" value="${businessKey}">
					<input type="hidden" id="creator" name="creator" value="${model.creator }">
					<!-- 流程数据 - End -->

					<div class="form-group">
						<label for="creatorName" class="col-sm-2 control-label">申请人</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="creatorName" name="creatorName" value="${model.creatorName }" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-2 control-label">请假类型</label>
						<div class="col-sm-4">
							<c:if test="${model.type == 1 }">年假</c:if>
							<c:if test="${model.type == 2 }">事假</c:if>
							<c:if test="${model.type == 3 }">病假</c:if>
							<c:if test="${model.type == 4 }">婚假</c:if>
							<c:if test="${model.type == 5 }">调休</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="applyDuration" class="col-sm-2 control-label">请假天数</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="applyDuration" name="applyDuration" value="${model.applyDuration }" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="descn" class="col-sm-2 control-label">请假原因</label>
						<div class="col-sm-4">
							<textarea rows="3" class="form-control" id="reason" name="reason" readonly="readonly">${model.reason }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="leaderAuditPass" class="col-sm-2 control-label">审批结果</label>
						<div class="col-sm-4">
							<select class="form-control" id="leaderAuditPass" name="pp_leaderAuditPass" onchange="changeLeaderAudit(this.value)">
								<option value="ONSELECT">请选择</option>
								<option value="1">同意</option>
								<option value="0">驳回</option>
							</select>
						</div>
					</div>
					<div class="form-group" id="auditRejectDiv">
						<label for="rejectReason" class="col-sm-2 control-label">驳回理由</label>
						<div class="col-sm-4">
							<textarea rows="3" class="form-control" id="rejectReason" name="rejectReason"></textarea>
						</div>
					</div>
					<div class="form-group" id="auditPassDiv">
						<label for="recorder" class="col-sm-2 control-label">指定记录人</label>
						<div class="col-sm-4">
							<select class="form-control" id="recorder" name="pp_recorder"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4">
							<button type="submit" class="btn btn-primary">完成</button>
						</div>
					</div>
				</form>

			</div>

		</div>

	</div>
	<!-- /.container-fluid -->
	
</body>
</html>
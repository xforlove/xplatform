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
		
	});

	function agree(){
		$('#approveResult').val('1');
		$('#businessForm').submit();
	}
	
	function reject(){
		$('#approveResult').val('0');
		$('#businessForm').submit();
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
					<input type="hidden" id="taskId" name="taskId" value="${param.taskId}">
					<input type="hidden" id="businessKey" name="businessKey" value="${param.businessKey}">
					<input type="hidden" id="businessType" name="businessType" value="vocationRequest">
					<input type="hidden" id="approveResult" name="approveResult">

					<div class="form-group">
						<label for="duration" class="col-sm-2 control-label">申请人ID</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="creator" name="creator" value="${param.creator }" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-2 control-label">请假类型</label>
						<div class="col-sm-4">
							<c:if test="${param.type == 1 }">
								年假
							</c:if>
							<c:if test="${param.type == 2 }">
								事假
							</c:if>
							<c:if test="${param.type == 3 }">
								病假
							</c:if>
							<c:if test="${param.type == 4 }">
								婚假
							</c:if>
							<c:if test="${param.type == 5 }">
								调休
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="duration" class="col-sm-2 control-label">请假天数</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="duration" name="duration" value="${param.duration }" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="descn" class="col-sm-2 control-label">请假原因</label>
						<div class="col-sm-4">
							<textarea rows="3" class="form-control" id="descn" name="descn" readonly="readonly">${param.descn }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4">
							<button type="submit" class="btn btn-primary" onclick="javascript: return agree();">通过</button>
							<button type="button" class="btn btn-danger" onclick="javascript: return reject();">拒绝</button>
							<button type="button" class="btn btn-default" onclick="javascript: history.back();">返回</button>
						</div>
					</div>
				</form>

			</div>

		</div>

	</div>
	<!-- /.container-fluid -->
	
</body>
</html>
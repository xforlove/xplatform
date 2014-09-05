<%@page import="org.apache.shiro.SecurityUtils"%>
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
<title><%=net.rockey.core.util.PropertyUtils.getInstance().getValue("project_name")%></title>
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

				<ol class="breadcrumb">
					<li><a href="javascript: void(0);">流程管理</a></li>
					<li><a href="javascript: location.href='bpm-process-list.do'">工作流程</a></li>
					<li class="active">编辑流程</li>
				</ol>

				<form class="form-horizontal" action="/${scopeUrl }/bpm/bpm-process-save.do" method="POST" role="form">
					<c:if test="${model != null}">
						<input type="hidden" id="id" name="id" value="${model.id}">
					</c:if>

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">流程名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="name" name="name" value="${model.name }">
						</div>
					</div>
					<div class="form-group">
						<label for="categoryIds" class="col-sm-2 control-label">流程分类</label>
						<div class="col-sm-4">
							<select id="bpmCategoryId" name="bpmCategoryId" class="singleselect" multiple="multiple">
								<c:forEach items="${bpmCategories}" var="item">
									<option value="${item.id}" ${item.id == model.bpmCategory.id ? 'selected' : ''}>${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="bpmConfBaseId" class="col-sm-2 control-label">绑定流程</label>
						<div class="col-sm-4">
							<select id="bpmConfBaseId" name="bpmConfBaseId" class="singleselect" multiple="multiple">
								<c:forEach items="${bpmConfBases}" var="item">
									<option value="${item.id}" ${item.id == model.bpmConfBase.id ? 'selected' : ''}>${item.processDefinitionId}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="priority" class="col-sm-2 control-label">排序</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="priority" name="priority" value="${model.priority }">
						</div>
					</div>
					<div class="form-group">
						<label for="useTaskConf" class="col-sm-2 control-label">配置任务负责人</label>
						<div class="col-sm-4">
							<span>
								<input type="radio" id="useTaskConf" name="useTaskConf" value="1" ${model.needTaskConf == 1 ? 'checked' : ''}>开启
								&nbsp;&nbsp;
								<input type="radio" id="useTaskConf" name="useTaskConf" value="0" ${model.needTaskConf != 1 ? 'checked' : ''}>关闭
							</span>
						</div>
					</div>
					<div class="form-group">
						<label for="descn" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-4">
							<textarea rows="3" class="form-control" id="descn" name="descn">${model.descn }</textarea>
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
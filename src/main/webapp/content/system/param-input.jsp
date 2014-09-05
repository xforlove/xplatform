<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
pageContext.setAttribute("currentHeader", "param");
pageContext.setAttribute("currentNavi", "param");
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

		initChkbox('statFlag', 'NORMAL');

		$('#statFlag').click(function(){
			chkboxOnclick('statFlag', 'NORMAL', 'CLOSE');
		});
	});
	
</script>
</head>

<body>

	<%@include file="/header/param.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/param.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="javascript: void(0);">参数设置</a></li>
					<li><a href="javascript: location.href='form-template-list.do'">参数列表</a></li>
					<li class="active">编辑参数</li>
				</ol>

				<form class="form-horizontal" action="/${scopeUrl }/param/param-save.do" method="POST" role="form">
					<c:if test="${model != null}">
						<input type="hidden" id="id" name="id" value="${model.id}">
					</c:if>
					
					<div class="form-group">
						<label for="code" class="col-sm-2 control-label">代号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="code" name="code" value="${model.code }">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="name" name="name" value="${model.name }">
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-2 control-label">类型</label>
						<div class="col-sm-4">
							<select class="form-control" id="type" name="type">
								<option value="ONSELECT">请选择</option>
								<option value="1" ${model.type == 1 ? 'selected' : ''}>布尔型</option>
								<option value="2" ${model.type == 2 ? 'selected' : ''}>整型</option>
								<option value="3" ${model.type == 3 ? 'selected' : ''}>双精度浮点型</option>
								<option value="4" ${model.type == 4 ? 'selected' : ''}>字符串型</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="value" class="col-sm-2 control-label">参数值</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="value" name="value" value="${model.value }">
						</div>
					</div>
					<div class="form-group">
						<label for="defValue" class="col-sm-2 control-label">默认值</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="defValue" name="defValue" value="${model.defValue }">
						</div>
					</div>
					<div class="form-group">
						<label for="descn" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-4">
							<textarea rows="3" class="form-control" id="descn" name="descn">${model.descn }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox col-sm-offset-2 col-sm-4">
							<label>
								<input type="checkbox" id="statFlag" name="statFlag" value="${model.statFlag }"> 启用
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
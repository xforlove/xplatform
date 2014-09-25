<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
pageContext.setAttribute("currentHeader", "doc");
pageContext.setAttribute("currentNavi", "doc");
%>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
<%@include file="/common/meta.jsp"%>
<title><%=net.rockey.core.util.PropertyUtils.getInstance().getValue("project_name") %></title>
<%@include file="/common/s.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#uploadify').uploadify({
			'uploader' : '<%=path %>/s/uploadify/uploadify.swf',
			'script' : 'doc-info-upload.do',
			'cancelImg': '<%=path %>/s/uploadify/cancel.png',
			'queueID' : 'fileQueue',
			'expressInstall' : '<%=path %>/s/uploadify/expressInstall.swf',
			'auto' : true,
			'multi' : false,
			onComplete : function(event, queueID, fileObj, response, data){
				alert('complete');
			}
		});
	});
	
	function submitForm(){
		// $('#uploadify').uploadifyUpload();
	}
</script>
</head>

<body>

	<%@include file="/header/doc-info.jsp"%>

	<div class="container-fluid">

		<div class="row">
			
			<%@include file="/navigation/doc-info.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="javascript: void(0);">文件管理</a></li>
					<li><a href="javascript: location.href='doc-info-list.do'">文件列表</a></li>
					<li class="active">编辑文件</li>
				</ol>

				<form class="form-horizontal" action="/${scopeUrl }/doc/doc-info-save.do" method="POST" role="form">
					<c:if test="${model != null}">
						<input type="hidden" id="id" name="id" value="${model.id}">
					</c:if>
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">请指定文件</label>
						<div class="col-sm-4">
							<input type="file" id="uploadify" name="uploadify" > 
							<div id="fileQueue"></div>
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
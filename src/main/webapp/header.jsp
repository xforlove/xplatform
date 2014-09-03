<%@page import="net.rockey.core.util.PropertyUtils"%>
<%@page contentType="text/html;charset=UTF-8"%>

<c:if test="${not empty flashMessages}">
	<div id="m-success-message" style="display:none;">
		<ul>
			<c:forEach items="${flashMessages}" var="item">
				 <li>${item}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<!-- start of header bar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="javascript: void(0);">
				<%=net.rockey.core.util.PropertyUtils.getInstance().getValue("project_name") %>
			</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="${currentHeader == 'dashboard' ? 'active' : ''}">
					<a href="/${scopeUrl }/dashboard/dashboard.do">
						<i class="glyphicon glyphicon-home"></i> 首页</a>
				</li>
				<li class="${currentHeader == 'workspace' ? 'active' : ''}">
					<a href="/${scopeUrl }/bpm/workspace-home.do">
						<i class="glyphicon glyphicon-user"></i> 个人事务 </a>
				</li>
				<li class="${currentHeader == 'report' ? 'active' : ''}">
					<a href="#">
						<i class="glyphicon glyphicon-list-alt"></i> 统计报表 </a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-cog"></i> 系统配置 <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">组织结构</a></li>
						<li class="${currentHeader == 'auth' ? 'active' : ''}"><a href="/${scopeUrl }/auth/index.do">权限体系</a></li>
						<li class="${currentHeader == 'bpm-console' ? 'active' : ''}"><a href="/${scopeUrl }/bpm/bpm-category-list.do"">流程管理</a></li>
						<li class="${currentHeader == 'form' ? 'active' : ''}"><a href="/${scopeUrl }/form-template/form-template-list.do"">表单管理</a></li>
						<li class="divider"></li>
						<li><a href="#">系统日志</a></li>
						<li><a href="#">参数设置</a></li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><shiro:principal /></a></li>
				<li><a href="${ctx }/logout.do">退出</a></li>
			</ul>
			<form class="navbar-form">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>
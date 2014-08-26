<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<shiro:hasPermission name="user:list">
		<li class="${currentNavi == 'user' ? 'active' : ''}">
			<a href="/${scopeUrl }/auth/user-list.do">
				<i class="glyphicon glyphicon-user"></i> 用户列表</a>
		</li>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="role:list">  
		<li class="${currentNavi == 'role' ? 'active' : ''}">
			<a href="/${scopeUrl }/auth/role-list.do">
				<i class="glyphicon glyphicon-user"></i> 角色列表</a>
		</li>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="funcgroup:list">
		<li class="${currentNavi == 'funcgroup' ? 'active' : ''}">
			<a href="/${scopeUrl }/app/funcgroup-list.do">
				<i class="glyphicon glyphicon-list"></i> 功能组列表</a>
		</li>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="function:list">
		<li class="${currentNavi == 'function' ? 'active' : ''}">
			<a href="/${scopeUrl }/app/function-list.do">
				<i class="glyphicon glyphicon-list"></i> 功能列表</a>
		</li>
		</shiro:hasPermission>
		
	</ul>
</div>
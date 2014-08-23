<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="${currentNavi == 'user' ? 'active' : ''}">
			<a href="/${scopeUrl }/auth/user-list.do">用户列表</a>
		</li>
		<li class="${currentNavi == 'role' ? 'active' : ''}">
			<a href="/${scopeUrl }/auth/role-list.do">角色列表</a>
		</li>
		<li class="${currentNavi == 'funcgrp' ? 'active' : ''}">
			<a href="/${scopeUrl }/app/funcgroup-list.do">功能组列表</a>
		</li>
		<li class="${currentNavi == 'func' ? 'active' : ''}">
			<a href="/${scopeUrl }/app/function-list.do">功能列表</a>
		</li>
		<li class="${currentNavi == 'menu' ? 'active' : ''}">
			<a href="/${scopeUrl }/app/menu-list.do">菜单列表</a>
		</li>
	</ul>
</div>
<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<li class="${currentNavi == 'param' ? 'active' : ''}">
			<a href="/${scopeUrl }/param/param-list.do">
				<i class="glyphicon glyphicon-list"></i> 参数列表</a>
		</li>
		
	</ul>
</div>
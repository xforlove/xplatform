<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<li class="${currentNavi == 'doc' ? 'active' : ''}">
			<a href="/${scopeUrl }/doc/doc-info-list.do">
				<i class="glyphicon glyphicon-list"></i> 文件列表</a>
		</li>
		
	</ul>
</div>
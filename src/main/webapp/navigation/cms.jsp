<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<li class="${currentNavi == 'cms-catalog' ? 'active' : ''}">
			<a href="/${scopeUrl }/cms/cms-catalog-list.do">
				<i class="glyphicon glyphicon-list"></i> 栏目管理</a>
		</li>
		
		<li class="${currentNavi == 'cms-article' ? 'active' : ''}">
			<a href="/${scopeUrl }/cms/cms-article-list.do">
				<i class="glyphicon glyphicon-list"></i> 文章管理</a>
		</li>
		
		<li class="${currentNavi == 'cms-comment' ? 'active' : ''}">
			<a href="/${scopeUrl }/cms/cms-comment-list.do">
				<i class="glyphicon glyphicon-list"></i> 评论管理</a>
		</li>
		
	</ul>
</div>
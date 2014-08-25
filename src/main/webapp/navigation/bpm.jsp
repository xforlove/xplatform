<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<li class="${currentNavi == 'category' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/category-list.do">
				<i class="icon-user"></i> 流程分类列表</a>
		</li>
		
		<li class="${currentNavi == 'process' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/process-list.do">
				<i class="icon-user"></i> 流程列表</a>
		</li>
		
		<li class="${currentNavi == 'processInstance' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/processInstance-list.do">
				<i class="icon-user"></i> 流程实例</a>
		</li>
		
		<li class="${currentNavi == 'historicProcessInstance' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/historicProcessInstance-list.do">
				<i class="icon-user"></i> 历史流程实例</a>
		</li>
		
	</ul>
</div>
<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<li class="${currentNavi == 'bpm-category' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/bpm-category-list.do">
				<i class="glyphicon glyphicon-list"></i> 流程分类</a>
		</li>
		
		<li class="${currentNavi == 'bpm-process' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/bpm-process-list.do">
				<i class="glyphicon glyphicon-list"></i> 工作流程</a>
		</li>
		
		<li class="${currentNavi == 'bpm-processInstance' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/console-listProcessInstances.do">
				<i class="glyphicon glyphicon-list"></i> 流程实例</a>
		</li>
		
		<li class="${currentNavi == 'bpm-historicProcessInstance' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/console-listHistoricProcessInstances.do">
				<i class="glyphicon glyphicon-list"></i> 历史流程实例</a>
		</li>
		
	</ul>
</div>
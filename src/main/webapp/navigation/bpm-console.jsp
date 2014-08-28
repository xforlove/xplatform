<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<li class="${currentNavi == 'bpm-category' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/bpm-category-list.do">
				<i class="glyphicon glyphicon-list"></i> 流程分类</a>
		</li>
		
		<li class="${currentNavi == 'bpm-process' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/bpm-process-list.do">
				<i class="glyphicon glyphicon-list"></i> 流程列表</a>
		</li>
		
		<li class="${currentNavi == 'bpm-mail-template' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/bpm-mail-template-list.do">
				<i class="glyphicon glyphicon-list"></i> 邮件模板</a>
		</li>
		
		<li class="${currentNavi == 'bpm-processDefinition' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/console-listProcessDefinitions.do">
				<i class="glyphicon glyphicon-list"></i> 流程定义</a>
		</li>
		
		<li class="${currentNavi == 'bpm-processInstance' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/console-listProcessInstances.do">
				<i class="glyphicon glyphicon-list"></i> 流程实例</a>
		</li>
		
		<li class="${currentNavi == 'bpm-task' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/console-listTasks.do">
				<i class="glyphicon glyphicon-list"></i> 任务</a>
		</li>
		
		<li class="${currentNavi == 'bpm-deployment' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/console-listDeployments.do">
				<i class="glyphicon glyphicon-list"></i> 部署</a>
		</li>
		
		<li class="${currentNavi == 'bpm-historicProcessInstance' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/console-listHistoricProcessInstances.do">
				<i class="glyphicon glyphicon-list"></i> 历史流程实例</a>
		</li>
		
		<li class="${currentNavi == 'bpm-historicTask' ? 'active' : ''}">
			<a href="/${scopeUrl }/bpm/console-listHistoricTasks.do">
				<i class="glyphicon glyphicon-list"></i> 历史任务</a>
		</li>
		
	</ul>
</div>
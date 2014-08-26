<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<li class="${currentNavi == 'process' ? 'active' : ''}">
			<a href="/${scopeUrl }/workspace/workspace-home.do">
				<i class="glyphicon glyphicon-list"></i> 发起流程
			</a>
		</li>
		
		<li class="${currentNavi == 'task-todo' ? 'active' : ''}">
			<a href="/${scopeUrl }/workspace/workspace-listPersonalTasks.do">
				<i class="glyphicon glyphicon-list"></i> 待办任务
			</a>
		</li>
		
		<li class="${currentNavi == 'task-done' ? 'active' : ''}">
			<a href="/${scopeUrl }/workspace/workspace-listHistoryTasks.do">
				<i class="glyphicon glyphicon-list"></i> 已办任务
			</a>
		</li>
		
		<li class="divider"></li>
		
		<li class="${currentNavi == 'calendar' ? 'active' : ''}">
			<a href="#">
				<i class="glyphicon glyphicon-calendar"></i> 日程管理
			</a>
		</li>
		
		<li class="${currentNavi == 'meeting' ? 'active' : ''}">
			<a href="#">
				<i class="glyphicon glyphicon-calendar"></i> 会议室管理
			</a>
		</li>
		
	</ul>
</div>
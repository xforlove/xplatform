<%@page contentType="text/html;charset=UTF-8"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		
		<li class="${currentNavi == 'bpm-process' ? 'active' : ''}">
			<a href="/${scopeUrl }/workspace/workspace-home.do">
				<i class="icon-user"></i> 发起流程
			</a>
		</li>
		
		<li class="${currentNavi == 'task-todo' ? 'active' : ''}">
			<a href="#">
				<i class="icon-user"></i> 待办任务
			</a>
		</li>
		
		<li class="${currentNavi == 'task-done' ? 'active' : ''}">
			<a href="#">
				<i class="icon-user"></i> 已办任务
			</a>
		</li>
		
		<li class="divider"></li>
		
		<li class="${currentNavi == 'calendar' ? 'active' : ''}">
			<a href="#">
				<i class="icon-user"></i> 日程管理
			</a>
		</li>
		
		<li class="${currentNavi == 'meeting' ? 'active' : ''}">
			<a href="#">
				<i class="icon-user"></i> 会议室管理
			</a>
		</li>
		
	</ul>
</div>
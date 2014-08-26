package net.rockey.bpm.listener;

import net.rockey.bpm.cmd.CompleteTaskWithCommentCmd;
import net.rockey.bpm.support.DefaultTaskListener;
import net.rockey.core.util.LogUtils;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;

public class AutoCompleteFirstTaskListener extends DefaultTaskListener {

	private static Logger log = LogUtils.getLogger(
			AutoCompleteFirstTaskListener.class, true);

	@Override
	public void onCreate(DelegateTask delegateTask) throws Exception {
		super.onCreate(delegateTask);

		String userId = ((Long) SecurityUtils.getSubject().getSession()
				.getAttribute("user_id")).toString();
		String assignee = delegateTask.getAssignee();

		ProcessDefinitionEntity processDefinitionEntity = Context
				.getProcessEngineConfiguration().getProcessDefinitionCache()
				.get(delegateTask.getProcessDefinitionId());

		ActivityImpl startActivity = processDefinitionEntity.getInitial();

		if (startActivity.getOutgoingTransitions().size() != 1) {
			throw new IllegalStateException(
					"start activity outgoing transitions cannot more than 1, now is : "
							+ startActivity.getOutgoingTransitions().size());
		}

		PvmTransition pvmTransition = startActivity.getOutgoingTransitions()
				.get(0);
		PvmActivity targetActivity = pvmTransition.getDestination();

		if (!"userTask".equals(targetActivity.getProperty("type"))) {
			log.debug("first activity is not userTask, just skip");

			return;
		}

		if (targetActivity.getId().equals(
				delegateTask.getExecution().getCurrentActivityId())) {
			if ((userId != null) && userId.equals(assignee)) {
				log.debug("auto complete first task : " + delegateTask);

				new CompleteTaskWithCommentCmd(delegateTask.getId(), null,
						"发起流程").execute(Context.getCommandContext());
			}
		}

	}
}

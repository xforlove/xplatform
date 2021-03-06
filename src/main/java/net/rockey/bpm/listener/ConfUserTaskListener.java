package net.rockey.bpm.listener;

import java.util.List;

import net.rockey.bpm.manager.BpmConfUserManager;
import net.rockey.bpm.model.BpmConfUser;
import net.rockey.bpm.support.DefaultTaskListener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.el.ExpressionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfUserTaskListener extends DefaultTaskListener {

	private static final Logger log = LoggerFactory
			.getLogger(ConfUserTaskListener.class);

	@Autowired
	private BpmConfUserManager bpmConfUserManager;

	@Override
	public void onCreate(DelegateTask delegateTask) throws Exception {
		List<BpmConfUser> bpmConfUsers = bpmConfUserManager
				.find("from BpmConfUser where bpmConfNode.bpmConfBase.processDefinitionId=? and bpmConfNode.code=?",
						delegateTask.getProcessDefinitionId(), delegateTask
								.getExecution().getCurrentActivityId());
		log.debug("bpmConfUsers : {}", bpmConfUsers);

		ExpressionManager expressionManager = Context
				.getProcessEngineConfiguration().getExpressionManager();

		try {
			for (BpmConfUser bpmConfUser : bpmConfUsers) {
				log.debug("status : {}, type : {}, value : {}",
						bpmConfUser.getStatus(), bpmConfUser.getType(),
						bpmConfUser.getValue());

				String value = expressionManager
						.createExpression(bpmConfUser.getValue())
						.getValue(delegateTask).toString();

				if (bpmConfUser.getStatus() == 1) {
					if (bpmConfUser.getType() == 0) {
						delegateTask.setAssignee(value);
					} else if (bpmConfUser.getType() == 1) {
						delegateTask.addCandidateUser(value);
					} else if (bpmConfUser.getType() == 2) {
						delegateTask.addCandidateGroup(value);
					}
				} else if (bpmConfUser.getStatus() == 2) {
					if (bpmConfUser.getType() == 0) {
						if (delegateTask.getAssignee().equals(value)) {
							delegateTask.setAssignee(null);
						}
					} else if (bpmConfUser.getType() == 1) {
						delegateTask.deleteCandidateUser(value);
					} else if (bpmConfUser.getType() == 2) {
						delegateTask.deleteCandidateGroup(value);
					}
				}
			}
		} catch (Exception ex) {
			log.debug(ex.getMessage(), ex);
		}

	}

}

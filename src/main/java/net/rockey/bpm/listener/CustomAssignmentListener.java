package net.rockey.bpm.listener;

import java.util.Map;

import net.rockey.bpm.support.DefaultTaskListener;

import org.activiti.engine.delegate.DelegateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomAssignmentListener extends DefaultTaskListener {

	private static final Logger log = LoggerFactory
			.getLogger(CustomAssignmentListener.class);

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO
		Map<String, Object> parameters = delegateTask.getVariables();

		log.info(parameters.toString());
		
		delegateTask.setAssignee("2");
	}

}

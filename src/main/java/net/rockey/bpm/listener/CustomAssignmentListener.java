package net.rockey.bpm.listener;

import java.util.Map;

import net.rockey.bpm.support.DefaultTaskListener;
import net.rockey.core.util.LogUtils;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.log4j.Logger;

public class CustomAssignmentListener extends DefaultTaskListener {

	private static Logger log = LogUtils.getLogger(
			CustomAssignmentListener.class, true);

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO
		Map<String, Object> parameters = delegateTask.getVariables();

		log.info(parameters.toString());
		
		delegateTask.setAssignee("2");
	}

}

package net.rockey.bpm.support;

import net.rockey.core.util.LogUtils;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.log4j.Logger;

public class DefaultTaskListener implements TaskListener {

	private static final Logger log = LogUtils.getLogger(
			DefaultTaskListener.class, true);

	@Override
	public void notify(DelegateTask delegateTask) {
		String eventName = delegateTask.getEventName();
		log.debug(this);
		log.debug(eventName + ", " + delegateTask);

		if ("create".equals(eventName)) {
			try {
				this.onCreate(delegateTask);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
		}

		if ("assignment".equals(eventName)) {
			try {
				this.onAssignment(delegateTask);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
		}

		if ("complete".equals(eventName)) {
			try {
				this.onComplete(delegateTask);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
		}

		if ("delete".equals(eventName)) {
			try {
				this.onDelete(delegateTask);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
		}

		((TaskEntity) delegateTask).setEventName(eventName);
	}

	public void onCreate(DelegateTask delegateTask) throws Exception {
	}

	public void onAssignment(DelegateTask delegateTask) throws Exception {
	}

	public void onComplete(DelegateTask delegateTask) throws Exception {
	}

	public void onDelete(DelegateTask delegateTask) throws Exception {
	}
}

package net.rockey.form.operation;

import java.util.Map;

import net.rockey.auth.manager.UserManager;
import net.rockey.core.spring.ApplicationContextHelper;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.Command;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public abstract class AbstractOperation<T> implements Operation<T>, Command<T> {

	private Map<String, Object> parameters;

	@Override
	public T execute(Map<String, Object> parameters) {
		this.parameters = parameters;

		ProcessEngine processEngine = getProcessEngine();

		return processEngine.getManagementService().executeCommand(this);
	}

	public String getParamValue(String name) {
		String value = (String) parameters.get(name);

		if ((value == null) || value.length() == 0) {
			return null;
		}

		return value;
	}

	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	public Long getCurrentUserId() {
		return (Long) SecurityUtils.getSubject().getSession()
				.getAttribute("user_id");
	}

	public ProcessEngine getProcessEngine() {
		return ApplicationContextHelper.getBean(ProcessEngine.class);
	}

	public UserManager getUserManager() {
		return ApplicationContextHelper.getBean(UserManager.class);
	}

}

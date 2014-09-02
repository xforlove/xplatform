package net.rockey.form.operation;

import java.util.Map;

import net.rockey.core.spring.ApplicationContextHelper;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.Command;

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

	public ProcessEngine getProcessEngine() {
		return ApplicationContextHelper.getBean(ProcessEngine.class);
	}

}

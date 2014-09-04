package net.rockey.form.operation;

import java.util.HashMap;
import java.util.Map;

import net.rockey.core.spring.ApplicationContextHelper;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.Command;

public abstract class AbstractOperation<T> implements Operation<T>, Command<T> {

	private Map<String, Object> parameters;
	private Map<String, Object> filteredParameters;
	private Map<String, Object> processParameters;

	@Override
	public T execute(Map<String, Object> parameters) {
		this.parameters = parameters;

		filterParameters();

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

	public String getFilteredParamValue(String name) {
		String value = (String) filteredParameters.get(name);

		if ((value == null) || value.length() == 0) {
			return null;
		}

		return value;
	}

	/**
	 * 过滤参数集合
	 * 
	 * @return
	 */
	public void filterParameters() {
		// init.
		this.filteredParameters = new HashMap<String, Object>();
		this.processParameters = new HashMap<String, Object>();

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();

			if (key.startsWith("pp_")) {
				String[] params = key.split("_");
				this.filteredParameters.put(params[1], entry.getValue());
				this.processParameters.put(params[1], entry.getValue());
			} else {
				this.filteredParameters.put(key, entry.getValue());
			}

		}

	}

	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	public Map<String, Object> getFilteredParameters() {
		return this.filteredParameters;
	}

	public Map<String, Object> getProcessParameters() {
		return this.processParameters;
	}

	public ProcessEngine getProcessEngine() {
		return ApplicationContextHelper.getBean(ProcessEngine.class);
	}

}

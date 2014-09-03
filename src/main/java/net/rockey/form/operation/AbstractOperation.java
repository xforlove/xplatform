package net.rockey.form.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.rockey.core.spring.ApplicationContextHelper;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.Command;

public abstract class AbstractOperation<T> implements Operation<T>, Command<T> {

	private Map<String, String[]> parameters;
	private Map<String, String[]> filteredParameters;

	@Override
	public T execute(Map<String, String[]> parameters) {
		this.parameters = parameters;
		this.filteredParameters = filterParameters();

		ProcessEngine processEngine = getProcessEngine();

		return processEngine.getManagementService().executeCommand(this);
	}

	public String getParamValue(String name) {
		String[] value = parameters.get(name);

		if ((value == null) || value.length == 0) {
			return null;
		}

		return value[0];
	}

	public String getFilteredParamValue(String name) {
		String[] value = filteredParameters.get(name);

		if ((value == null) || value.length == 0) {
			return null;
		}

		return value[0];
	}

	/**
	 * 过滤参数集合
	 * 
	 * @return
	 */
	public Map<String, String[]> filterParameters() {
		filteredParameters = new HashMap<String, String[]>();

		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String key = entry.getKey();

			if (key.startsWith("pp_")) {
				String[] params = key.split("_");
				this.filteredParameters.put(params[1], entry.getValue());
			} else {
				this.filteredParameters.put(key, entry.getValue());
			}

		}

		return this.filteredParameters;
	}

	public Map<String, String[]> getParameters() {
		return this.parameters;
	}

	public Map<String, String[]> getFilteredParameters() {
		return this.filteredParameters;
	}

	public ProcessEngine getProcessEngine() {
		return ApplicationContextHelper.getBean(ProcessEngine.class);
	}

}

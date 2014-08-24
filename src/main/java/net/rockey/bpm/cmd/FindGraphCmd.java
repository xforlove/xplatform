package net.rockey.bpm.cmd;

import net.rockey.bpm.graph.ActivitiGraphBuilder;
import net.rockey.bpm.graph.Graph;
import net.rockey.core.util.LogUtils;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.log4j.Logger;

public class FindGraphCmd implements Command<Graph> {

	private final Logger log = LogUtils.getLogger(FindGraphCmd.class, true);
	
	private String processDefinitionId;

	public FindGraphCmd(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	@Override
	public Graph execute(CommandContext commandContext) {
		return new ActivitiGraphBuilder(processDefinitionId).build();
	}

}

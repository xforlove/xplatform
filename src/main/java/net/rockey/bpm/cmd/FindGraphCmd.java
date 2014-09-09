package net.rockey.bpm.cmd;

import net.rockey.bpm.graph.ActivitiGraphBuilder;
import net.rockey.bpm.graph.Graph;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindGraphCmd implements Command<Graph> {

	private final static Logger log = LoggerFactory
			.getLogger(FindGraphCmd.class);
	
	private String processDefinitionId;

	public FindGraphCmd(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	@Override
	public Graph execute(CommandContext commandContext) {
		return new ActivitiGraphBuilder(processDefinitionId).build();
	}

}

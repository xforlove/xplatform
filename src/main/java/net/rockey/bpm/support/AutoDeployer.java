package net.rockey.bpm.support;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.PostConstruct;

import net.rockey.bpm.cmd.SyncProcessCmd;
import net.rockey.core.util.LogUtils;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ContextResource;
import org.springframework.core.io.Resource;

/**
 * 自动部署，并把每个xml都发布成一个Deployment.
 * 
 */
public class AutoDeployer {

	private final Logger log = LogUtils.getLogger(AutoDeployer.class, true);

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private Resource[] deploymentResources = new Resource[0];

	@Autowired
	private boolean enable = true;

	@PostConstruct
	public void init() {
		if (!enable) {
			return;
		}

		if ((deploymentResources == null) || (deploymentResources.length == 0)) {
			return;
		}

		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		for (Resource resource : deploymentResources) {
			String resourceName = null;

			if (resource instanceof ContextResource) {
				resourceName = ((ContextResource) resource)
						.getPathWithinContext();
			} else if (resource instanceof ByteArrayResource) {
				resourceName = resource.getDescription();
			} else {
				try {
					resourceName = resource.getFile().getAbsolutePath();
				} catch (IOException ex) {
					log.debug(ex.getMessage(), ex);
					resourceName = resource.getFilename();
				}
			}

			try {
				DeploymentBuilder deploymentBuilder = repositoryService
						.createDeployment().enableDuplicateFiltering()
						.name(resourceName);

				if (resourceName.endsWith(".bar")
						|| resourceName.endsWith(".zip")
						|| resourceName.endsWith(".jar")) {
					deploymentBuilder.addZipInputStream(new ZipInputStream(
							resource.getInputStream()));
				} else {
					deploymentBuilder.addInputStream(resourceName,
							resource.getInputStream());
				}

				Deployment deployment = deploymentBuilder.deploy();
				log.info("auto deploy : " + resourceName);

				for (ProcessDefinition processDefinition : repositoryService
						.createProcessDefinitionQuery()
						.deploymentId(deployment.getId()).list()) {
					this.syncProcessDefinition(processDefinition.getId());
				}
			} catch (IOException ex) {
				throw new ActivitiException("couldn't auto deploy resource '"
						+ resource + "': " + ex.getMessage(), ex);
			}
		}
	}

	public boolean checkDeploymentUpToDate(String resourceName,
			long lastModified) {
		List<Deployment> deployments = processEngine.getRepositoryService()
				.createDeploymentQuery().deploymentName(resourceName)
				.orderByDeploymenTime().desc().list();

		if (deployments.isEmpty()) {
			return false;
		}

		Deployment deployment = deployments.get(0);

		return deployment.getDeploymentTime().getTime() > lastModified;
	}

	public void syncProcessDefinition(String processDefinitionId) {
		processEngine.getManagementService().executeCommand(
				new SyncProcessCmd(processDefinitionId));
	}
}

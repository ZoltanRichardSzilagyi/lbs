package hu.zrs.lbs.agent;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.BuildAgent;
import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.task.TaskTypeMapFactory;

@Configuration
public class AgentConfiguration {

	@Bean
	TaskTypeMapFactory taskTypeMapFactory() {
		return new TaskTypeMapFactory();
	}


	@Bean("taskTypes")
	public Map<String, Class<Task>> taskTypes(@Autowired final TaskTypeMapFactory taskTypeMapFactory) {
		return taskTypeMapFactory.createTaskTypeMap("hu.zrs.lbs.task.build");
	}

	@Bean
	public Agent agent(@Value("${agent.id}") final String agentId, @Value("${agent.name}") final String agentName, @Value("${agent.url}") final String agentUrl)
			throws URISyntaxException {
		final BuildAgent agent = new BuildAgent(agentId);
		agent.setName(agentName);
		// agent.setUptime(LocalDate.now());
		agent.setInstallationPath(Paths.get("/implement/it"));
		agent.setUri(new URI(agentUrl));
		return agent;
	}

}

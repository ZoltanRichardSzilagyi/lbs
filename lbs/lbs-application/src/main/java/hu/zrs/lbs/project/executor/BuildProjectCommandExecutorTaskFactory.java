package hu.zrs.lbs.project.executor;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentRegistry;
import hu.zrs.lbs.api.agent.criteria.AgentAndCriteria;
import hu.zrs.lbs.api.agent.criteria.AgentCriteria;
import hu.zrs.lbs.api.agent.criteria.AgentIsAvailableCriteria;
import hu.zrs.lbs.api.agent.criteria.AgentIsIdleCriteria;
import hu.zrs.lbs.api.project.executor.CommandExecutionQueue;

@Component
public class BuildProjectCommandExecutorTaskFactory {

	private static final Logger logger = LoggerFactory.getLogger(BuildProjectCommandExecutor.class);

	@Autowired
	private AgentRegistry agentRegistry;

	@Autowired
	private CommandExecutionQueue<ExecuteProjectCommand> commandExecutionQueue;


	public Runnable createTask() {
		return () -> {try {
			final ExecuteProjectCommand command = commandExecutionQueue.take();
			final AgentCriteria agentCriteria = new AgentAndCriteria(new AgentIsAvailableCriteria(), new AgentIsIdleCriteria());
			final Collection<Agent> agents = agentRegistry.getAgent(agentCriteria);

			if (!agents.isEmpty()) {
				final Agent agent = agents.iterator().next();
				agent.setToIdle();
			} else {
				logger.info("Could not find available agent");
			}
		} catch (final Exception exception) {
			logger.error(exception.getMessage(), exception);
		}
		};
	}

}

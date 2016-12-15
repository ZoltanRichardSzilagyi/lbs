package hu.zrs.lbs.agent;

import java.util.ArrayList;
import java.util.List;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentRegistry;
import hu.zrs.lbs.api.agent.AgentService;
import hu.zrs.lbs.api.agent.AgentStatus;

public class BuildAgentService implements AgentService {

	private AgentRegistry agentRegistry;

	@Override
	public List<Agent> getAgent() {
		final Iterable<Agent> registeredAgents = agentRegistry.getAllAgents();
		final List<Agent> agents = new ArrayList<>();
		registeredAgents.forEach(agent -> {
			if (AgentStatus.AVAILABLE.equals(agent.getStatus())) {
				agents.add(agent);
			}
		});
		return agents;
	}

}

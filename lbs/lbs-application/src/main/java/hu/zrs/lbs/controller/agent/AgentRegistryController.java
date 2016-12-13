package hu.zrs.lbs.controller.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentRegistry;
import hu.zrs.lbs.api.agent.AgentStatus;
import hu.zrs.lbs.api.agent.BuildAgent;
import hu.zrs.lbs.api.agent.GenericAgentProperties;

@RestController
@RequestMapping("/agent")
public class AgentRegistryController {

	@Autowired
	private AgentRegistry agentRegistry;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Agent> getAgent() {
		return agentRegistry.getAllAgents();
	}

	@RequestMapping(method = RequestMethod.POST)
	public GenericAgentProperties registerAgent(@RequestBody final BuildAgent agent) {
		final GenericAgentProperties agentProperties = new GenericAgentProperties();
		agent.setProperties(agentProperties);
		agentRegistry.register(agent);
		agent.setStatus(AgentStatus.REGISTERED);

		return agentProperties;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void unregisterAgent(@RequestBody final BuildAgent agent) {
		agentRegistry.unregister(agent);
		agent.setStatus(AgentStatus.UNREGISTERED);
	}

}

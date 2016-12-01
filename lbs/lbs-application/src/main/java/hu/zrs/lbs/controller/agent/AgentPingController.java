package hu.zrs.lbs.controller.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentRegistry;
import hu.zrs.lbs.api.agent.AgentStatus;
import hu.zrs.lbs.api.agent.GenericAgent;

@RestController
@RequestMapping("/agent/ping")
public class AgentPingController {

	private static final Logger logger = LoggerFactory.getLogger(AgentPingController.class);

	@Autowired
	private AgentRegistry agentRegistry;

	@RequestMapping(method = RequestMethod.POST)
	public AgentStatus ping(@RequestBody final GenericAgent agentParameter) {
		AgentStatus agentStatusResponse = null;
		final Agent agent = agentRegistry.getAgent(agentParameter);
		if (agent != null) {
			final AgentStatus registeredStatus = agent.getStatus();
			if (registeredStatus.isAgentRegistered()) {
				agentStatusResponse = AgentStatus.AVAILABLE;
				agent.setStatus(agentStatusResponse);
			} else if (registeredStatus.isAgentUnregistered()) {
				agentStatusResponse = AgentStatus.UNREGISTERED;
			}
		} else {
			logger.info("Unable to handle unregistered agent: {}", agentParameter.getName());
		}
		return agentStatusResponse;
	}

}

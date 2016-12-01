package hu.zrs.lbs.agent.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentStatus;
import hu.zrs.lbs.api.agent.GenericAgentProperties;

@Component
public class AgentRegistryClient {

	private static final Logger logger = LoggerFactory.getLogger(AgentRegistryClient.class);

	@Value("${build.server.agent.url}")
	private String buildServerAgentUrl;

	@Autowired
	private Agent agent;

	public void registerAgent() {
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<GenericAgentProperties> agentPopertiesResponse = restTemplate.postForEntity(buildServerAgentUrl, agent, GenericAgentProperties.class);
		if (agentPopertiesResponse.getStatusCode().is2xxSuccessful()) {
			final GenericAgentProperties agentProperties = agentPopertiesResponse.getBody();
			agent.setProperties(agentProperties);
			agent.setStatus(AgentStatus.REGISTERED);
		} else {
			logger.error("Client registration is unsuccessful. Status code: ", agentPopertiesResponse.getStatusCodeValue());
		}
	};



}

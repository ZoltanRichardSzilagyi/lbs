package hu.zrs.lbs.agent.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentStatus;

public class PingClient {

	@Value("${build.server.agent.ping.url}")
	private String pingUrl;

	@Autowired
	private Agent agent;

	public void pingBuildServer() {
		final RestTemplate restTemplate = new RestTemplate();
		// TODO send only agent id as request parameter
		final ResponseEntity<AgentStatus> pingResponse = restTemplate.postForEntity(pingUrl, agent, AgentStatus.class);
		if (pingResponse.getStatusCode().is2xxSuccessful()) {
			agent.setStatus(pingResponse.getBody());
		}
	}
}

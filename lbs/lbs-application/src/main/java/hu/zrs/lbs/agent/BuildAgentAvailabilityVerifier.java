package hu.zrs.lbs.agent;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentAvailabilityVerifier;
import hu.zrs.lbs.api.agent.AgentRegistry;
import hu.zrs.lbs.api.agent.AgentStatus;

@Component
public class BuildAgentAvailabilityVerifier implements AgentAvailabilityVerifier {

	@Autowired
	private AgentRegistry agentRegistry;

	@Override
	public List<Agent> verify() {
		final List<Agent> unavailableAgents = new ArrayList<>();
		agentRegistry.getAllAgents().forEach(agent -> {
			if (!AgentStatus.AVAILABLE.equals(agent.getStatus())) {
				unavailableAgents.add(agent);
			} else {
				final LocalDate timeOfLastActivity = agent.getTimeOfLastActivity();
				final LocalDate activityValidationTreshold = LocalDate.now().minus(10l, ChronoUnit.SECONDS);
				if (activityValidationTreshold.isAfter(timeOfLastActivity)) {
					unavailableAgents.add(agent);
				}

			}
		});
		return unavailableAgents;
	}

}

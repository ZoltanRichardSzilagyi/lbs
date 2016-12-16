package hu.zrs.lbs.agent;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentRegistry;
import hu.zrs.lbs.api.agent.criteria.AgentCriteria;

@Component
public class BuildAgentRegistry implements AgentRegistry {

	private static final Logger logger = LoggerFactory.getLogger(BuildAgentRegistry.class);

	private final Set<Agent> agents = Collections.synchronizedSet(new HashSet<>());

	@Override
	public Agent getAgent(final Agent agent) {
		return agents.contains(agent) ? agent : null;
	}

	@Override
	public Iterable<Agent> getAllAgents() {
		return Collections.unmodifiableSet(agents);
	}

	@Override
	public void register(final Agent agent) {
		if (!agents.contains(agent)) {
			agents.add(agent);
		} else {
			logger.info("Agent {} is already registered", agent.getId());
		}
	}

	@Override
	public void unregister(final Agent agent) {
		agents.remove(agent);
	}

	@Override
	// TODO it has to be an atomic operation
	public Collection<Agent> getAgent(final AgentCriteria criteria) {
		return agents.stream().filter(agent -> criteria.evaluate(agent)).collect(Collectors.toList());
	}

}

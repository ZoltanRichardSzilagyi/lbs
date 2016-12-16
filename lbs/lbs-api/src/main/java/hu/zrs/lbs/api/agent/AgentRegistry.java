package hu.zrs.lbs.api.agent;

import java.util.Collection;

import hu.zrs.lbs.api.agent.criteria.AgentCriteria;

public interface AgentRegistry {

	public Agent getAgent(Agent agent);

	public Iterable<Agent> getAllAgents();

	public void register(Agent agent);

	public void unregister(Agent agent);

	public Collection<Agent> getAgent(AgentCriteria criteria);


}

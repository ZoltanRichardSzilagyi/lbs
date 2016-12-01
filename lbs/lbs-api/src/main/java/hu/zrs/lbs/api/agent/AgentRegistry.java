package hu.zrs.lbs.api.agent;

public interface AgentRegistry {

	public Agent getAgent(Agent agent);

	public Iterable<Agent> getAllAgents();

	public void register(Agent agent);

	public void unregister(Agent agent);



}

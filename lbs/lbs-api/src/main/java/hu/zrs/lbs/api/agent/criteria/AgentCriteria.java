package hu.zrs.lbs.api.agent.criteria;

import hu.zrs.lbs.api.agent.Agent;

public interface AgentCriteria {

	boolean evaluate(Agent agent);

}

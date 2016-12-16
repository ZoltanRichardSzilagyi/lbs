package hu.zrs.lbs.api.agent.criteria;

import hu.zrs.lbs.api.agent.Agent;

public class AgentIsIdleCriteria implements AgentCriteria {

	@Override
	public boolean evaluate(final Agent agent) {
		return agent.isIdle();
	}

}

package hu.zrs.lbs.api.agent.criteria;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentStatus;

public class AgentIsAvailableCriteria implements AgentCriteria {

	@Override
	public boolean evaluate(final Agent agent) {
		return AgentStatus.AVAILABLE.equals(agent.getStatus());
	}

}

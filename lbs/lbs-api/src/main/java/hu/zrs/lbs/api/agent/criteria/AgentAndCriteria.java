package hu.zrs.lbs.api.agent.criteria;

import hu.zrs.lbs.api.agent.Agent;

public class AgentAndCriteria implements AgentCriteria {

	private final AgentCriteria leftHandSideCriteria;

	private final AgentCriteria rightHandSideCriteria;

	public AgentAndCriteria(final AgentCriteria leftHandSideCriteria, final AgentCriteria rightHandSideCriteria) {
		this.leftHandSideCriteria = leftHandSideCriteria;
		this.rightHandSideCriteria = rightHandSideCriteria;
	}

	@Override
	public boolean evaluate(final Agent agent) {
		return leftHandSideCriteria.evaluate(agent) && rightHandSideCriteria.equals(agent);
	}

}

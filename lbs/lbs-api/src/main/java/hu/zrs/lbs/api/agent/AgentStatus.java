package hu.zrs.lbs.api.agent;

public enum AgentStatus {

	UNKNOWN(0), UNREGISTERED(1), REGISTERED(2), AVAILABLE(3), UNAVAILABLE(4);

	private final int statusCode;

	private AgentStatus(final int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isAgentRegistered() {
		return statusCode >= 2;
	}

	public boolean isAgentUnregistered() {
		return statusCode == 1;
	}
}

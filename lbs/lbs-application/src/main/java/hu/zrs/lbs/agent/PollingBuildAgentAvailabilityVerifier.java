package hu.zrs.lbs.agent;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.zrs.lbs.api.agent.Agent;
import hu.zrs.lbs.api.agent.AgentAvailabilityVerifier;

public class PollingBuildAgentAvailabilityVerifier {

	private static final Logger logger = LoggerFactory.getLogger(PollingBuildAgentAvailabilityVerifier.class);

	private static final Long POLLING_INTERVAL_IN_MS = 1_000L;

	private final AgentAvailabilityVerifier verifier;

	private final ScheduledExecutorService pollingExecutor = Executors.newScheduledThreadPool(1);

	public PollingBuildAgentAvailabilityVerifier(final AgentAvailabilityVerifier verifier) {
		this.verifier = verifier;
	}

	public void startPolling() {
		pollingExecutor.scheduleWithFixedDelay(() -> {
			logger.info("Verifying list of unavailable agents.");
			final List<Agent> unavailableAgents = verifier.verify();
			unavailableAgents.forEach(agent -> logger.error("Agent {} is not available.", agent.getId()));
		} , 1000, POLLING_INTERVAL_IN_MS, TimeUnit.MILLISECONDS);
	}

	public void destroy() {
		pollingExecutor.shutdown();
	}


}

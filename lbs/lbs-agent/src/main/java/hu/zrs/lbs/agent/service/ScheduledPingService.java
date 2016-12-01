package hu.zrs.lbs.agent.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import hu.zrs.lbs.agent.client.PingClient;

public class ScheduledPingService implements PingService {

	private final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

	@Autowired
	private PingClient pingClient;

	@Override
	public void ping() {
		scheduledExecutor.scheduleWithFixedDelay(() -> {
			pingClient.pingBuildServer();
		} , 0, 1000, TimeUnit.MILLISECONDS);
	}

}

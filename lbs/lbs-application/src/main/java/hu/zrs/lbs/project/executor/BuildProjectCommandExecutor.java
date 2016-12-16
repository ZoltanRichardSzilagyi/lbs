package hu.zrs.lbs.project.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.project.executor.CommandExecutionQueue;

@Component
public class BuildProjectCommandExecutor {

	private static final int NUMBER_OF_WORKER_THREADS = 4;

	private static final Logger logger = LoggerFactory.getLogger(BuildProjectCommandExecutor.class);

	private final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_WORKER_THREADS);

	private boolean stop = false;

	@Autowired
	private CommandExecutionQueue<ExecuteProjectCommand> commandExecutionQueue;

	public void startExecution() {
		for (int i = 0; i < NUMBER_OF_WORKER_THREADS; i++) {
			executor.submit(() -> {
				while (!stop) {
					try {
						final ExecuteProjectCommand command = commandExecutionQueue.take();
					} catch (final Exception exception) {
						logger.error(exception.getMessage(), exception);
					}
				}
			});
		}
	}

	public void stopExecution() {
		stop = true;
	}

}

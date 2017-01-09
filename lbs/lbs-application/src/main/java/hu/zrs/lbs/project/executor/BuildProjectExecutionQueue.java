package hu.zrs.lbs.project.executor;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.project.executor.CommandExecutionQueue;

@Component
public class BuildProjectExecutionQueue implements CommandExecutionQueue<ExecuteProjectCommand> {

	private static final Logger logger = LoggerFactory.getLogger(BuildProjectExecutionQueue.class);

	private final BlockingDeque<ExecuteProjectCommand> commandQueue = new LinkedBlockingDeque<>();

	@Override
	public void add(final ExecuteProjectCommand executionCommand) {
		commandQueue.add(executionCommand);
	}

	@Override
	public ExecuteProjectCommand take() throws InterruptedException {
		ExecuteProjectCommand command = null;
		try {
			command = commandQueue.takeLast();
		} catch (final InterruptedException exception) {
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		return command;
	}

	@Override
	public void remove(final ExecuteProjectCommand executionCommand) {
		commandQueue.remove(executionCommand);
	}

}

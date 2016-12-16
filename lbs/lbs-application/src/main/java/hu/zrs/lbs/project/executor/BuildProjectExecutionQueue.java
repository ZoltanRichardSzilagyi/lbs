package hu.zrs.lbs.project.executor;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.project.executor.CommandExecutionQueue;

@Component
public class BuildProjectExecutionQueue implements CommandExecutionQueue<ExecuteProjectCommand> {

	private final BlockingDeque<ExecuteProjectCommand> commandQueue = new LinkedBlockingDeque<>();

	@Override
	public void add(final ExecuteProjectCommand executionCommand) {
		commandQueue.add(executionCommand);
	}

	@Override
	public ExecuteProjectCommand take() {
		ExecuteProjectCommand command = null;
		try {
			command = commandQueue.takeLast();
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
		return command;
	}

	@Override
	public void remove(final ExecuteProjectCommand executionCommand) {
		commandQueue.remove(executionCommand);
	}

}

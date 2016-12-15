package hu.zrs.lbs.project.execution;

import java.util.List;

import hu.zrs.lbs.api.project.execution.CommandExecutionQueue;

public class BuildProjectExecutionQueue implements CommandExecutionQueue<ExecuteProjectCommand> {

	@Override
	public void add(final ExecuteProjectCommand executionCommand) {

	}

	@Override
	public List<ExecuteProjectCommand> poll() {
		return null;
	}

	@Override
	public void remove(final ExecuteProjectCommand executionCommand) {

	}

}

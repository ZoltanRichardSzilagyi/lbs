package hu.zrs.lbs.project.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.project.executor.CommandExecutionQueue;
import hu.zrs.lbs.api.project.executor.CommandExecutor;

@Component
public class BuildProjectExecutorService implements CommandExecutor<ExecuteProjectCommand> {

	@Autowired
	private CommandExecutionQueue<ExecuteProjectCommand> executionQueue;

	@Override
	public void execute(final ExecuteProjectCommand command) {
		// TODO audit the fact of the execution
		executionQueue.add(command);
	}

}

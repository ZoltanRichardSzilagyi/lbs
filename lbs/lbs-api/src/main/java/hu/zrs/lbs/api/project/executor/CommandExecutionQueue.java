package hu.zrs.lbs.api.project.executor;

import hu.zrs.lbs.api.command.Command;

public interface CommandExecutionQueue<C extends Command> {

	void add(C executionCommand);

	C take() throws InterruptedException;

	void remove(C executionCommand);

}

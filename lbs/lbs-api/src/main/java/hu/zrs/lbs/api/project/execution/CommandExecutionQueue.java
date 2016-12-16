package hu.zrs.lbs.api.project.execution;

public interface CommandExecutionQueue<C extends Command> {

	void add(C executionCommand);

	C take();

	void remove(C executionCommand);

}

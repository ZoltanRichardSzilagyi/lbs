package hu.zrs.lbs.api.project.execution;

import java.util.List;

public interface CommandExecutionQueue<C extends Command> {

	void add(C executionCommand);

	List<C> poll();

	void remove(C executionCommand);

}

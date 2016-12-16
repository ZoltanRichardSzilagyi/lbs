package hu.zrs.lbs.api.project.executor;

public interface CommandExecutor<C> {
	
	void execute(C command);

}

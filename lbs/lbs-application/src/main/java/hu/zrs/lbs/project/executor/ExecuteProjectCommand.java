package hu.zrs.lbs.project.executor;

import hu.zrs.lbs.api.project.execution.Command;
import hu.zrs.lbs.entity.persistence.Project;

public class ExecuteProjectCommand extends Command<Project> {


	public ExecuteProjectCommand(final Project request) {
		super(request);
	}

}

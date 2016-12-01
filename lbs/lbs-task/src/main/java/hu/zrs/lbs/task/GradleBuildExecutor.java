package hu.zrs.lbs.task;

import java.util.Arrays;
import java.util.List;

import hu.zrs.lbs.api.task.TaskAttribute;

public class GradleBuildExecutor extends GradleTaskExecutor {

	@TaskAttribute
	private String includedBuild;

	public GradleBuildExecutor() {
	}

	public GradleBuildExecutor(final String name, final String includedBuild, final String taskToExecute) {
		super(name, Arrays.asList(taskToExecute));
		this.includedBuild = includedBuild;
	}

	public GradleBuildExecutor(final String name, final String includedBuild, final List<String> tasksToExecute) {
		super(name, tasksToExecute);
		this.includedBuild = includedBuild;
	}

	public String getIncludedBuild() {
		return includedBuild;
	}

	public void setIncludedBuild(final String includedBuild) {
		this.includedBuild = includedBuild;
	}

}

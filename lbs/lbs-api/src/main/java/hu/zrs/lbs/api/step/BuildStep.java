package hu.zrs.lbs.api.step;

import java.util.List;

import hu.zrs.lbs.api.task.Task;

public class BuildStep implements Step {

	public BuildStep(final String name, final List<Task> tasks) {
		this.name = name;
		this.tasks = tasks;
	}

	private final String name;

	private final List<Task> tasks;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Task> getTasks() {
		return tasks;
	}

}

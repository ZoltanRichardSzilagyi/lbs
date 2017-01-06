package hu.zrs.lbs.task.build;

import java.util.Arrays;
import java.util.List;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.task.TaskAttribute;

public class GradleTaskExecutor implements Task{

	private String name;

	@TaskAttribute
	private List<String> tasksToExecute;

	public GradleTaskExecutor() {
	}

	public GradleTaskExecutor(final String name, final List<String> tasksToExecute) {
		this.name = name;
		this.tasksToExecute = tasksToExecute;
	}

	public GradleTaskExecutor(final String name, final String taskToExecute) {
		this.name = name;
		this.tasksToExecute = Arrays.asList(taskToExecute);
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	public List<String> getTasksToExecute() {
		return tasksToExecute;
	}

	public void setTasksToExecute(final List<String> tasksToExecute) {
		this.tasksToExecute = tasksToExecute;
	}

}

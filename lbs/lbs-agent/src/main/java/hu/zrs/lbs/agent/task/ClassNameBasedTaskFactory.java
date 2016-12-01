package hu.zrs.lbs.agent.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.task.TaskFactory;

@Component
public class ClassNameBasedTaskFactory implements TaskFactory {

	@Autowired
	@Qualifier("taskTypes")
	private final Map<String, Class<Task>> taskTypes;

	public ClassNameBasedTaskFactory(@Autowired @Qualifier("taskTypes") final Map<String, Class<Task>> taskTypes) {
		this.taskTypes = taskTypes;
	}


	@Override
	public Task createTask(final String taskType) {
		final Class<Task> resolvedTaskType = taskTypes.get(taskType);
		if (resolvedTaskType == null) {
			throw new IllegalArgumentException("No matching task type found for: " + taskType);
		}
		Task task = null;
		try {
			task = resolvedTaskType.newInstance();
		} catch (InstantiationException | IllegalAccessException exception) {
			throw new RuntimeException("Unable to instantiate task: " + taskType, exception);
		}
		return task;
	}

}

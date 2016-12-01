package hu.zrs.lbs.agent.task;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import hu.zrs.lbs.agent.tasks.BarTask;
import hu.zrs.lbs.agent.tasks.FakeTask;
import hu.zrs.lbs.agent.tasks.FooTask;
import hu.zrs.lbs.api.task.Task;

public class TaskTypeMapFactoryTest {

	private static final String TASK_PACKAGE = "hu.zrs.lbs.agent.tasks";

	private final TaskTypeMapFactory taskTypeMapFactory = new TaskTypeMapFactory();

	@Test
	public void shoulProvideTaskTypeMap() {
		final Map<String, Class<Task>> taskTypeMap = taskTypeMapFactory.createTaskTypeMap(TASK_PACKAGE);

		Assert.assertTrue(taskTypeMap.get(FooTask.class.getSimpleName()) != null);
		Assert.assertTrue(taskTypeMap.get(BarTask.class.getSimpleName()) != null);
		Assert.assertTrue(taskTypeMap.get(FakeTask.class.getSimpleName()) == null);
	}

}

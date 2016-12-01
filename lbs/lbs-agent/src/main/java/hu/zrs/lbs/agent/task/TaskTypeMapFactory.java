package hu.zrs.lbs.agent.task;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import hu.zrs.lbs.api.task.Task;

public class TaskTypeMapFactory {

	public Map<String, Class<Task>> createTaskTypeMap(final String taskPackageName) {

		final String taskPackagePath = taskPackageName.replace('.', '/');
		final URL taskPackage = Thread.currentThread().getContextClassLoader().getResource(taskPackagePath);
		final File taskPackageFile = new File(taskPackage.getFile());
		final Map<String, Class<Task>> taskTypes = collectTaskImplementations(taskPackageName, taskPackageFile);
		return taskTypes;
	}

	private Map<String, Class<Task>> collectTaskImplementations(final String taskPackageName, final File taskPackageFile) {
		final Map<String, Class<Task>> taskTypes = new HashMap<>();
		Arrays.asList(taskPackageFile.listFiles()).forEach(classFile -> {
			loadTaskType(taskPackageName, taskTypes, classFile);
		});
		return taskTypes;
	}

	private void loadTaskType(final String taskPackageName, final Map<String, Class<Task>> taskTypes, final File classFile) {
		final String taskName = StringUtils.substringBefore(classFile.getName(), ".");
		final String taskClassName = taskPackageName + "." + taskName;
		Class<?> taskClass = null;
		try {
			taskClass = Class.forName(taskClassName);
		} catch (final Exception exception) {
			throw new RuntimeException("Tasks types cannot be determined due to:", exception);
		}
		if (Task.class.isAssignableFrom(taskClass)) {
			taskTypes.put(taskName, (Class<Task>) taskClass);
		}
	}

}

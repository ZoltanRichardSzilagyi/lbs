package hu.zrs.lbs.api.task;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// TODO move to task module, create interface in api module
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
		final String className = classFile.getName();
		final String taskName = className.substring(0, className.lastIndexOf("."));
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

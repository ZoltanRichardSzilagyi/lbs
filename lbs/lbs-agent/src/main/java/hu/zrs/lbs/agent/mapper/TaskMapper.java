package hu.zrs.lbs.agent.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.task.TaskFactory;
import hu.zrs.lbs.entity.persistence.TaskAttribute;

@Component
public class TaskMapper implements EntityMapper<List<hu.zrs.lbs.entity.persistence.Task>, List<Task>> {

	@Autowired
	private TaskFactory taskFactory;

	@Override
	public List<Task> map(final List<hu.zrs.lbs.entity.persistence.Task> tasks) {
		return tasks.stream().map(task -> {
			return instantiateTask(task);
		}).collect(Collectors.toList());
	}

	private Task instantiateTask(final hu.zrs.lbs.entity.persistence.Task task) {
		final Task buildTask = taskFactory.createTask(task.getType());
		buildTask.setName(task.getName());

		final Map<String, String> taskAttributeMap = task.getAttributes().stream().collect(Collectors.toMap(TaskAttribute::getName, TaskAttribute::getValue));
		initializeTaskAttributes(buildTask, taskAttributeMap);
		return buildTask;
	}

	private void initializeTaskAttributes(final Task buildTask, final Map<String, String> taskAttributeMap) {
		Arrays.asList(buildTask.getClass().getDeclaredFields()).forEach(taskField -> {
			if (taskField.isAnnotationPresent(hu.zrs.lbs.api.task.TaskAttribute.class)) {
				final String fieldName = taskField.getName();
				final Class fieldType = taskField.getType();

				final String attributeValue = taskAttributeMap.get(fieldName);
				final ObjectMapper objectMapper = new ObjectMapper();
				try {
					final Object deserializedAttributeValue = objectMapper.readValue(attributeValue, fieldType);
					taskField.set(buildTask, deserializedAttributeValue);
				} catch (final Exception exception) {
					throw new RuntimeException(exception);
				}
			}
		});
	}

}

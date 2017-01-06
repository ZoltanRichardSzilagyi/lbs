package hu.zrs.lbs.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.task.TaskAttribute;
import hu.zrs.lbs.api.task.TaskAttributeDescriptor;
import hu.zrs.lbs.api.task.TaskDescriptor;
import hu.zrs.lbs.api.task.TaskDescriptorService;
import hu.zrs.lbs.api.task.TaskDescriptor;
import hu.zrs.lbs.api.task.TaskTypeMapFactory;

@Component
public class BuildTaskDescriptorService implements TaskDescriptorService {

	private static final Logger logger = LoggerFactory.getLogger(BuildTaskDescriptorService.class);

	@Autowired
	private TaskTypeMapFactory taskTypeMapFactory;

	@Override
	public Collection<TaskDescriptor> getTaskDescriptors() {
		final Map<String, Class<Task>> taskTypes = taskTypeMapFactory.createTaskTypeMap("hu.zrs.lbs.task.build");
		final Collection<TaskDescriptor> taskIdentifiers = taskTypes.entrySet().stream().map(taskTypeEntry -> {
			final String taskName = taskTypeEntry.getKey();
			final Class<Task> taskType = taskTypeEntry.getValue();

			final String taskId = DigestUtils.md5DigestAsHex(taskName.getBytes());
			final List<TaskAttributeDescriptor> taskAttributeDescriptors = new ArrayList<>();

			final String name = null;
			final String description = null;

			final TaskDescriptor taskTypeDescriptor = new TaskDescriptor(taskId, name, description, taskType,
					taskAttributeDescriptors);
			taskAttributeDescriptors.addAll(getTaskAttributeIdentifiers(taskTypeDescriptor));
			return taskTypeDescriptor;
		}).collect(Collectors.toList());
		return taskIdentifiers;
	}

	private Collection<TaskAttributeDescriptor> getTaskAttributeIdentifiers(final TaskDescriptor taskTypeDescriptor) {
		return Arrays.asList(taskTypeDescriptor.getType().getDeclaredFields()).stream().map(field -> {
			TaskAttributeDescriptor attributeDescriptor = null;
			if (field.isAnnotationPresent(TaskAttribute.class)) {
				final String attributeName = field.getName();
				final String attributeId = DigestUtils.md5DigestAsHex(attributeName.getBytes());
				attributeDescriptor = new TaskAttributeDescriptor(attributeId, taskTypeDescriptor.getId(), attributeName, "title", "description");
			}else{
				logger.warn(field.getName() + " field of task" + taskTypeDescriptor.getName() + " is ignored.");
			}
			return attributeDescriptor;
		}).filter(descriptor -> descriptor != null).collect(Collectors.toList());
	}
}

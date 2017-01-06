package hu.zrs.lbs.task.csv;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.task.TaskAttributeDescriptor;

@Component
public class CSVTaskAttributeDescriptorSerializer extends AbstractCSVTaskSerializer<TaskAttributeDescriptor> {

	private final Collection<String> fields = Arrays.asList("id", "task_id", "name", "title", "description");

	@Override
	protected Function<TaskAttributeDescriptor, Collection<String>> getRowProvider() {
		return descriptor -> Arrays.asList(descriptor.getId(), descriptor.getTaskTypeDescriptorId(), descriptor.getName(), descriptor.getTitle(), descriptor.getDescription());
	}

	@Override
	protected Collection<String> getHeaderColumns() {
		return Collections.unmodifiableCollection(fields);
	}

}

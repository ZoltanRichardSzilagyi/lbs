package hu.zrs.lbs.task.csv;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.task.TaskDescriptor;

@Component
public class CSVTaskTypeDescriptorSerializer extends AbstractCSVTaskSerializer<TaskDescriptor> {
	
	private final Collection<String> fields = Arrays.asList("id", "type", "name", "description");

	@Override
	protected Function<TaskDescriptor, Collection<String>> getRowProvider() {
		return taskDescriptor -> Arrays.asList(taskDescriptor.getId(), taskDescriptor.getType().getSimpleName(), taskDescriptor.getName(), taskDescriptor.getDescription());

	}

	@Override
	protected Collection<String> getHeaderColumns() {
		return Collections.unmodifiableCollection(fields);
	}
}
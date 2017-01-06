package hu.zrs.lbs.task.csv;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import hu.zrs.lbs.api.task.TaskDescriptorSerializer;

public abstract class AbstractCSVTaskSerializer<T> implements TaskDescriptorSerializer<T, CSVSerializedTaskDescriptor> {
	
	@Value("${csv.separator}")
	private String separator;
	
	@Value("${csv.quotechar}")
	private String quoteChar;

	protected abstract Collection<String> getHeaderColumns();
	

	protected abstract Function<T, Collection<String>> getRowProvider();
	
	@Override
	public CSVSerializedTaskDescriptor serialize(final Collection<T> taskDescriptors) {
		StringBuilder rows = new StringBuilder();
		rows.append(createHeader());
		rows.append(System.lineSeparator());

		taskDescriptors.forEach(taskDescriptor -> {
			rows.append(
			StringUtils.collectionToDelimitedString(
					getRowProvider().apply(taskDescriptor).stream().map(value -> quoteChar+value+quoteChar).
					collect(Collectors.toList()), 
					separator) 
			);
			rows.append(System.lineSeparator());			
		});
		InputStream serializedDescriptor = new ByteArrayInputStream(rows.toString().getBytes());
		return new CSVSerializedTaskDescriptor(getHeaderColumns(), serializedDescriptor);
	}
	
	private String createHeader() {
		// TODO refactor double looping
		return StringUtils.collectionToDelimitedString(getHeaderColumns().stream().map(field -> quoteChar + field + quoteChar).collect(Collectors.toList()),
				separator);
	}

}

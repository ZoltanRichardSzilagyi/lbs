package hu.zrs.lbs.task.csv;

import java.io.InputStream;
import java.util.Collection;

public class CSVSerializedTaskDescriptor {

	private final Collection<String> fields;
	private final InputStream stream;

	public CSVSerializedTaskDescriptor(final Collection<String> fields, final InputStream stream) {
		this.fields = fields;
		this.stream = stream;
	}

	public Collection<String> getFields() {
		return fields;
	}

	public InputStream getStream() {
		return stream;
	}

}

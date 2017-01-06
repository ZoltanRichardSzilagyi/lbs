package hu.zrs.lbs.api.task;

import java.util.Collection;

public interface TaskDescriptorSerializer<T, R> {

	R serialize(final Collection<T> taskDescriptor);

}

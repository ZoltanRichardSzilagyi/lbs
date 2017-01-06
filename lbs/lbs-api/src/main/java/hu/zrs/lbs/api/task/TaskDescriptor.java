package hu.zrs.lbs.api.task;

import java.util.Collection;

public class TaskDescriptor {

	private final String id;

	private final String name;

	private final Class<? extends Task> type;

	private final String description;

	private final Collection<TaskAttributeDescriptor> attributeIdentifiers;

	public TaskDescriptor(final String id, final String name, final String description, final Class<? extends Task> type,
			final Collection<TaskAttributeDescriptor> attributeIdentifiers) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.attributeIdentifiers = attributeIdentifiers;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Class<? extends Task> getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public Collection<TaskAttributeDescriptor> getAttributeIdentifiers() {
		return attributeIdentifiers;
	}

}

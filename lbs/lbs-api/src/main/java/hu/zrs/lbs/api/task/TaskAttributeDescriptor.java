package hu.zrs.lbs.api.task;

public class TaskAttributeDescriptor {

	private final String id;

	private final String taskTypeDescriptorId;

	private final String name;

	private String title;

	private final String description;

	public TaskAttributeDescriptor(final String id, final String taskTypeDescriptorId, final String name, final String title, final String description) {
		this.id = id;
		this.taskTypeDescriptorId = taskTypeDescriptorId;
		this.name = name;
		this.description = description;
	}


	public String getId() {
		return id;
	}

	public String getTaskTypeDescriptorId() {
		return taskTypeDescriptorId;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

}

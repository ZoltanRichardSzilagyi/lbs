package hu.zrs.lbs.controller.executor;

public class ProjectExecutionStatus {
	
	private final Integer executionId;
	
	private final Integer projectId;
	
	private final boolean success;

	private ProjectExecutionStatus(final Integer executionId, final Integer projectId, final boolean success) {
		this.executionId = executionId;
		this.projectId = projectId;
		this.success = success;
	}

	public static ProjectExecutionStatus successExecution(final Integer executionId, final Integer projectId) {
		return new ProjectExecutionStatus(executionId, projectId, true);
	}

	public static ProjectExecutionStatus unsuccessExecution(final Integer executionId, final Integer projectId) {
		return new ProjectExecutionStatus(executionId, projectId, false);
	}

	public Integer getExecutionId() {
		return executionId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public boolean isSuccess() {
		return success;
	}
	
}

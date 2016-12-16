package hu.zrs.lbs.controller.executor;

import java.util.Random;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.zrs.lbs.api.project.executor.CommandExecutor;
import hu.zrs.lbs.entity.persistence.Project;
import hu.zrs.lbs.project.executor.ExecuteProjectCommand;
import hu.zrs.lbs.repository.ProjectRepository;

@RestController
@RequestMapping("/api/project/{projectId}/execute")
public class ProjectExecutorController {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private CommandExecutor<ExecuteProjectCommand> projectExecutorService;

	@RequestMapping(method = RequestMethod.PUT)
	public ProjectExecutionStatus execute(@PathVariable final Integer projectId) {
		return executeCommand(projectId, projectExecutorService::execute);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void stopExecution(@PathVariable final Integer projectId) {
		executeCommand(projectId, projectExecutorService::execute);
	}

	private ProjectExecutionStatus executeCommand(final Integer projectId, final Consumer<ExecuteProjectCommand> queueOperation) {
		final Project project = projectRepository.findOne(projectId);
		ProjectExecutionStatus executionStatus = null;
		final Integer executionId = new Random().nextInt();
		if (project != null) {
			final ExecuteProjectCommand executeCommand = new ExecuteProjectCommand(project, executionId);
			queueOperation.accept(executeCommand);
			executionStatus = ProjectExecutionStatus.successExecution(executionId, projectId);
		} else {
			executionStatus = ProjectExecutionStatus.unsuccessExecution(executionId, projectId);
		}
		return executionStatus;
	}

}

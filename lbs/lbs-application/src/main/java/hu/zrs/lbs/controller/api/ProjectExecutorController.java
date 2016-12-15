package hu.zrs.lbs.controller.api;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.zrs.lbs.api.project.execution.CommandExecutionQueue;
import hu.zrs.lbs.entity.persistence.Project;
import hu.zrs.lbs.project.execution.ExecuteProjectCommand;
import hu.zrs.lbs.repository.ProjectRepository;

@RequestMapping("/api/project/{projectId}/execute")
public class ProjectExecutorController {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private CommandExecutionQueue<ExecuteProjectCommand> projectExecutionQueue;

	@RequestMapping(method = RequestMethod.PUT)
	public void execute(@PathVariable final Integer projectId) {
		executeCommand(projectId, projectExecutionQueue::add);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void stopExecution(@PathVariable final Integer projectId) {
		executeCommand(projectId, projectExecutionQueue::remove);
	}

	private void executeCommand(final Integer projectId, final Consumer<ExecuteProjectCommand> queueOperation) {
		final Project project = projectRepository.findOne(projectId);
		if (project != null) {
			final ExecuteProjectCommand executeCommand = new ExecuteProjectCommand(project);
			queueOperation.accept(executeCommand);
		} else {
			// TODO sent back code 500
		}
		// TODO send response

	}

}

package hu.zrs.lbs.controller.api;

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
	public void execute(@PathVariable final Integer projectId) {
		executeCommand(projectId, projectExecutorService::execute);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void stopExecution(@PathVariable final Integer projectId) {
		executeCommand(projectId, projectExecutorService::execute);
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

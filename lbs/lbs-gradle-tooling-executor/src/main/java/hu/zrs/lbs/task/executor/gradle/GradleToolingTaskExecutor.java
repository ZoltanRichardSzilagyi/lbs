package hu.zrs.lbs.task.executor.gradle;

import java.io.File;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import hu.zrs.lbs.api.task.executor.ExecutionLog;
import hu.zrs.lbs.api.task.executor.ExecutorConfiguration;
import hu.zrs.lbs.api.task.executor.TaskExecutor;

public class GradleToolingTaskExecutor implements TaskExecutor {

	private final File projectPath;

	public GradleToolingTaskExecutor(final File projectPath) {
		this.projectPath = projectPath;
	}

	@Override
	public ExecutionLog execute(final ExecutorConfiguration configuration) {
		final ExecutionLog executionResult = new ExecutionLog();
		final ProjectConnection connection = openProjectConnection();
		final BuildLauncher buildLauncher = createBuildLauncher(configuration, connection);

		try {
			buildLauncher.run();
		} catch (final RuntimeException exception) {
			executionResult.addExecutionErrorLog(exception);
		}
		return executionResult;
	}

	private ProjectConnection openProjectConnection() {
		final GradleConnector gradleConnector = GradleConnector.newConnector().forProjectDirectory(projectPath);
		return gradleConnector.connect();
	}

	private BuildLauncher createBuildLauncher(final ExecutorConfiguration configuration, final ProjectConnection connection) {
		final BuildLauncher buildLauncher = connection.newBuild();
		buildLauncher.forTasks(configuration.getTasks().toArray(new String[] {}));

		buildLauncher.setStandardOutput(configuration.getErrorOutput());
		buildLauncher.setStandardError(configuration.getErrorOutput());

		return buildLauncher;
	}

}

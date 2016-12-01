package hu.zrs.lbs.project.generator.gradle;

import java.io.File;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;
import org.gradle.tooling.model.build.BuildEnvironment;
import org.gradle.tooling.model.gradle.GradleBuild;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GradleToolingApiTest {
	
	private static final Logger logger = LoggerFactory.getLogger(GradleToolingApiTest.class);

	@Test
	public void testBuildProject() {
		final String projectPath = "/home/zrs/lbs/test-build";
		final GradleConnector gradleConnector = GradleConnector.newConnector().forProjectDirectory(new File(projectPath));
		final ProjectConnection connection = gradleConnector.connect();

		connection.model(BuildEnvironment.class).get();

		connection.model(GradleBuild.class).get().getProjects().forEach(p -> {
			logger.info(p.toString());
		});
		final BuildLauncher buildLauncher = connection.newBuild();
		final GradleProject project = connection.model(GradleProject.class).get();

		buildLauncher.addProgressListener(new org.gradle.tooling.events.ProgressListener() {

			@Override
			public void statusChanged(final org.gradle.tooling.events.ProgressEvent event) {
				// logger.info(event.getDisplayName());
			}
		});
		buildLauncher.forTasks("executeProject");
		buildLauncher.setStandardError(System.err);
		buildLauncher.setStandardOutput(System.out);
		buildLauncher.run();
	}

}

package hu.zrs.lbs.project.generator.gradle;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.zrs.lbs.api.project.BuildProject;
import hu.zrs.lbs.api.step.BuildStep;
import hu.zrs.lbs.api.step.Step;
import hu.zrs.lbs.task.build.Copy;
import hu.zrs.lbs.task.build.GradleBuildExecutor;
import hu.zrs.lbs.task.build.GradleTaskExecutor;
import hu.zrs.lbs.task.build.ShellExecute;

@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
@ContextConfiguration(classes = GradleProjectGeneratorIntegrationTestConfiguration.class)
public class GradleProjectGeneratorIntegrationTest { 
	
	private static final Logger logger = LoggerFactory.getLogger(GradleProjectGeneratorIntegrationTest.class);

	@Autowired
	private ProjectGenerator projectGenerator;

	@Test
	public void shouldGenerateProject() {
		final String projectPath = "/home/zrs/lbs";

		final Step deployStep = createDeployStep();
		final Step buildStep = createBuildStep();
		
		final BuildProject buildProject = new BuildProject(1, "test-build");
		buildProject.setSteps(Arrays.asList(buildStep, deployStep));

		final ProjectGeneratorContext generatorContext = new ProjectGeneratorContext(buildProject, Paths.get(projectPath));
		projectGenerator.generate(buildProject, generatorContext);

	}

	private Step createDeployStep() {
		final ShellExecute echoTask = new ShellExecute("echoTask", "echo");
		final ShellExecute echoVersionTask = new ShellExecute("echoVersion", "echo");
		final Copy copyTask = new Copy("configCopy", "/home/zrs/lbs/config", "/home/zrs/lbs/deploy", "application.properties");
		final GradleTaskExecutor taskExecutorTask = new GradleTaskExecutor("taskExecutor", Arrays.asList("echoTask", "configCopy", "echoVersion"));
		final Step deployStep = new BuildStep("deployEnvironment", Arrays.asList(echoTask, echoVersionTask, copyTask, taskExecutorTask));
		return deployStep;
	}

	private Step createBuildStep() {
		final Copy projectCopyTask = new Copy("projectCopy", "/home/zrs/dev/java/sts/lbs", "/home/zrs/lbs/test-build/workingDirectory/lbs", "*");
		final GradleTaskExecutor projectBuildTaskExecutor = new GradleBuildExecutor("buildProject", "lbs", "jar");

		final Step buildStep = new BuildStep("buildProject", Arrays.asList(projectCopyTask, projectBuildTaskExecutor));
		return buildStep;
	}

}
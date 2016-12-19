package hu.zrs.lbs.project.generator.gradle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.zrs.lbs.api.project.Project;
import hu.zrs.lbs.api.project.generator.Generator;
import hu.zrs.lbs.api.translator.Translator;
import hu.zrs.lbs.api.translator.resolver.TranslatorResolver;

public class ProjectGenerator implements Generator<Project, ProjectGeneratorContext> {

	private static final Logger logger = LoggerFactory.getLogger(ProjectGenerator.class);

	private final TranslatorResolver translatorResolver;

	private final StepGenerator stepGenerator;

	public ProjectGenerator(final TranslatorResolver translatorResolver, final StepGenerator stepGenerator) {
		this.translatorResolver = translatorResolver;
		this.stepGenerator = stepGenerator;
	}

	@Override
	public void generate(final Project project, final ProjectGeneratorContext generatorContext) {
		createProjectDirectory(generatorContext);
		createBuildFile(generatorContext);
		createWorkingDirectory(generatorContext);
	
		translateProjectConfiguration(generatorContext);
		generateSteps(generatorContext);
		createSettingsFile(generatorContext);
	}

	private void createProjectDirectory(final ProjectGeneratorContext generatorContext) {
		final Project project = generatorContext.getProject();
		final Path projectRootPath = generatorContext.getProjectParentFolder().resolve(project.getName());
		final File projectRootDirectory = projectRootPath.toFile();

		try {
			FileUtils.forceDelete(projectRootDirectory);
		} catch (final IOException exception) {
			logger.error(exception.getMessage(), exception);
		}

		projectRootDirectory.mkdirs();
		generatorContext.setProjectRootDirectory(projectRootDirectory);
	}
	
	private void createWorkingDirectory(final ProjectGeneratorContext generatorContext){
		final File workingDirectory = new File(generatorContext.getProjectRootDirectory(), "workingDirectory");
		workingDirectory.mkdir();
	}

	private void createBuildFile(final ProjectGeneratorContext generatorContext) {
		final File buildFile = new File(generatorContext.getProjectRootDirectory(), "build.gradle");
		try {
			buildFile.createNewFile();
		} catch (final IOException exception) {
			logger.error(exception.getMessage(), exception);
		}
		generatorContext.setProjectBuildFile(buildFile);
	}

	private void translateProjectConfiguration(final ProjectGeneratorContext generatorContext) {
		final Translator<Project> projectTranslator = translatorResolver.resolve(generatorContext.getProject());
		final String translatedProject = projectTranslator.translate(generatorContext.getProject());
		final File buildFile = generatorContext.getProjectBuildFile();

		try (FileWriter buildFileWriter = new FileWriter(buildFile);) {
			buildFileWriter.write(translatedProject);
		} catch (final IOException exception) {
			logger.error(exception.getMessage(), exception);
		}
	}

	private void generateSteps(final ProjectGeneratorContext generatorContext) {
		generatorContext.getProject().getSteps().forEach(step -> {
			stepGenerator.generate(step, generatorContext);
		});
	}

	private void createSettingsFile(final ProjectGeneratorContext generatorContext) {
		final File settingsFile = BuildFileUtil.createBuildSettingsFile(generatorContext.getProjectRootDirectory());

		final StringBuilder settings = new StringBuilder();
		generatorContext.getIncludedProjects().forEach(projectName -> {
			settings.append("include '" + projectName + "'");
			settings.append("\n");
		});

		try (FileWriter fileWriter = new FileWriter(settingsFile);) {
			fileWriter.write(settings.toString());
		} catch (final IOException e) {
			e.printStackTrace();

		}

	}
}
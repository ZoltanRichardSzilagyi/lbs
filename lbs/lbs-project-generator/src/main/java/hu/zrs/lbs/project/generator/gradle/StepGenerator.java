package hu.zrs.lbs.project.generator.gradle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.zrs.lbs.api.project.generator.Generator;
import hu.zrs.lbs.api.step.Step;
import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.translator.Translatable;
import hu.zrs.lbs.api.translator.Translator;
import hu.zrs.lbs.api.translator.resolver.TranslatorResolver;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilder;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilderFactory;

public class StepGenerator implements Generator<Step, ProjectGeneratorContext> {

	private static final Logger logger = LoggerFactory.getLogger(StepGenerator.class);

	private final TranslatorResolver translatorResolver;

	private final TemplateBuilderFactory templateBuilderFactory;

	public StepGenerator(final TranslatorResolver translatorResolver, final TemplateBuilderFactory templateBuilderFactory) {
		this.translatorResolver = translatorResolver;
		this.templateBuilderFactory = templateBuilderFactory;
	}

	@Override
	public void generate(final Step step, final ProjectGeneratorContext generatorContext) {
		final File projectDirectory = createProjectDirectory(step, generatorContext);
		final File buildFile = BuildFileUtil.createBuildFile(projectDirectory);
		generateBuildFile(step, buildFile);
		generatorContext.addProjectToInclude(step.getName());
	}

	private File createProjectDirectory(final Step step, final ProjectGeneratorContext generatorContext) {
		final File projectDirectory = new File(generatorContext.getProjectRootDirectory(), step.getName());
		projectDirectory.mkdir();
		return projectDirectory;
	}

	private void generateBuildFile(final Step step, final File buildFile) {
		try (FileWriter fileWriter = new FileWriter(buildFile)) {
			final String translatedStep = translateStep(step);
			fileWriter.write(translatedStep);
		} catch (final IOException exception) {
			logger.error(exception.getMessage(), exception);
		}
	}

	private String translateStep(final Step step) {
		final TemplateBuilder templateBuilder = templateBuilderFactory.getBuilder("GradleStep.gtpl");
		final String translatedTasks = translateTasks(step);
		templateBuilder.addParameter("tasks", translatedTasks);
		templateBuilder.addParameter("step", step);
		return templateBuilder.getContent();
	}

	private String translateTasks(final Step step) {
		final StringBuilder translatedTasks = new StringBuilder();
		for (final Task task : step.getTasks()) {
			final Translator<Translatable> taskTranslator = translatorResolver.resolve(task);
			translatedTasks.append(taskTranslator.translate(task));
			translatedTasks.append("\n");
		}
		return translatedTasks.toString();
	}



}

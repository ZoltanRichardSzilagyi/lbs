package hu.zrs.lbs.project.generator.gradle;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.zrs.lbs.api.project.BuildProject;
import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.translator.Translator;
import hu.zrs.lbs.api.translator.resolver.TranslatorResolver;
import hu.zrs.lbs.project.generator.gradle.ProjectGenerator;
import hu.zrs.lbs.project.generator.gradle.StepGenerator;
import hu.zrs.lbs.task.ShellExecute;
import hu.zrs.lbs.translator.gradle.project.BuildProjectTranslator;
import hu.zrs.lbs.translator.gradle.resolver.GradleTranslatorResolver;
import hu.zrs.lbs.translator.gradle.task.DefaultTaskTranslator;
import hu.zrs.lbs.translator.gradle.task.ShellExecuteTaskTranslator;
import hu.zrs.lbs.translator.gradle.template.ClasspathTemplateProvider;
import hu.zrs.lbs.translator.gradle.template.DefaultTemplateBuilderFactory;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilderFactory;
import hu.zrs.lbs.translator.gradle.template.TemplateProvider;

@Configuration
public class GradleProjectGeneratorIntegrationTestConfiguration {

	@Bean
	public ClasspathTemplateProvider templateProvider() {
		return new ClasspathTemplateProvider("translator-template/");
	}

	@Bean
	public DefaultTemplateBuilderFactory templateBuilderFactory(@Autowired TemplateProvider templateProvider) {
		return new DefaultTemplateBuilderFactory(templateProvider);
	}

	@Bean
	public Map<Class<?>, Translator<?>> registeredTranslators(@Autowired TemplateBuilderFactory templateBuilderFactory) {
		final Map<Class<?>, Translator<?>> translators = new HashMap<>();
		translators.put(BuildProject.class, new BuildProjectTranslator(templateBuilderFactory));
		translators.put(Task.class, new DefaultTaskTranslator(templateBuilderFactory));
		translators.put(ShellExecute.class, new ShellExecuteTaskTranslator(templateBuilderFactory));
		return translators;
	}

	@Bean
	public TranslatorResolver translatorResolver(final Map<Class<?>, Translator<?>> translators) {
		return new GradleTranslatorResolver(translators);
	}

	@Bean
	public StepGenerator stepGenerator(@Autowired TranslatorResolver translatorResolver, @Autowired TemplateBuilderFactory templateBuilderFactory) {
		return new StepGenerator(translatorResolver, templateBuilderFactory);
	}

	@Bean
	public ProjectGenerator projectGenerator(@Autowired TranslatorResolver translatorResolver, @Autowired StepGenerator stepGenerator) {
		return new ProjectGenerator(translatorResolver, stepGenerator);
	}

}

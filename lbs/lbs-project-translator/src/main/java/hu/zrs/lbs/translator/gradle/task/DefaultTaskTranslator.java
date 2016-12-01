package hu.zrs.lbs.translator.gradle.task;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.translator.Translator;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilder;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilderFactory;

public class DefaultTaskTranslator implements Translator<Task> {

	private final TemplateBuilderFactory templateBuilderFactory;

	public DefaultTaskTranslator(final TemplateBuilderFactory templateBuilderFactory) {
		this.templateBuilderFactory = templateBuilderFactory;
	}

	@Override
	public String translate(Task task) {
		final TemplateBuilder templateBuilder = templateBuilderFactory.getBuilder(task.getClass().getSimpleName() + ".gtpl");
		templateBuilder.addParameter("task", task);
		return templateBuilder.getContent();
	}

}

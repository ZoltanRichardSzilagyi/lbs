package hu.zrs.lbs.translator.gradle.task;

import hu.zrs.lbs.api.translator.Translator;
import hu.zrs.lbs.task.ShellExecute;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilder;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilderFactory;

public class ShellExecuteTaskTranslator implements Translator<ShellExecute> {

	private final TemplateBuilderFactory templateBuilderFactory;

	public ShellExecuteTaskTranslator(TemplateBuilderFactory templateBuilderFactory) {
		this.templateBuilderFactory = templateBuilderFactory;
	}

	@Override
	public String translate(ShellExecute shellExecuteTask) {
		final TemplateBuilder templateBuilder = templateBuilderFactory.getBuilder("ShellExecuteTask.gtpl");
		templateBuilder.addParameter("task", shellExecuteTask);
		return templateBuilder.getContent();
	}

}

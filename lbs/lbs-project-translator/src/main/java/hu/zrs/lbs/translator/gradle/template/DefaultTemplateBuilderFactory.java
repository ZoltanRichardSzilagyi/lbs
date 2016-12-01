package hu.zrs.lbs.translator.gradle.template;

import org.apache.velocity.Template;

public class DefaultTemplateBuilderFactory implements TemplateBuilderFactory {

	private final TemplateProvider<Template> templateProvider;

	public DefaultTemplateBuilderFactory(TemplateProvider<Template> templateProvider) {
		this.templateProvider = templateProvider;
	}

	@Override
	public TemplateBuilder getBuilder(String templateName) {
		final Template template = templateProvider.getTemplate(templateName);
		return new DefaultTemplateBuilder(template);
	}

}

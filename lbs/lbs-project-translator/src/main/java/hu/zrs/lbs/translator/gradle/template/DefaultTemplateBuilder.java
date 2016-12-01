package hu.zrs.lbs.translator.gradle.template;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class DefaultTemplateBuilder implements TemplateBuilder {

	private final Template template;

	private final VelocityContext templateContext;

	DefaultTemplateBuilder(Template template) {
		this.template = template;
		this.templateContext = new VelocityContext();
	}

	@Override
	public void addParameter(String key, Object parameter) {
		templateContext.put(key, parameter);
	}

	@Override
	public String getContent() {
		String content = null;
		try (final StringWriter stringWriter = new StringWriter()) {
			template.merge(templateContext, stringWriter);
			content = stringWriter.toString();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
		return content;
	}

}

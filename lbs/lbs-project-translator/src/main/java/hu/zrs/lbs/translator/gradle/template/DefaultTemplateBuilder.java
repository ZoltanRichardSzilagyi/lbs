package hu.zrs.lbs.translator.gradle.template;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTemplateBuilder implements TemplateBuilder {

	private static final Logger logger = LoggerFactory.getLogger(DefaultTemplateBuilder.class);

	private final Template template;

	private final VelocityContext templateContext;

	DefaultTemplateBuilder(final Template template) {
		this.template = template;
		this.templateContext = new VelocityContext();
	}

	@Override
	public void addParameter(final String key, final Object parameter) {
		templateContext.put(key, parameter);
	}

	@Override
	public String getContent() {
		String content = null;
		try (final StringWriter stringWriter = new StringWriter()) {
			template.merge(templateContext, stringWriter);
			content = stringWriter.toString();
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return content;
	}

}

package hu.zrs.lbs.translator.gradle.template;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClasspathTemplateProvider implements TemplateProvider<Template> {

	private static final Logger logger = LoggerFactory.getLogger(ClasspathTemplateProvider.class);

	private final VelocityEngine velocityEngine;

	private final String templateDirectory;

	public ClasspathTemplateProvider(final String templateDirectory) {
		this.templateDirectory = templateDirectory;
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

	}

	@Override
	public Template getTemplate(final String templateName) {
		Template template = null;
		try {
			template = velocityEngine.getTemplate(templateDirectory + templateName);
		} catch (final Exception exception) {
			logger.error(exception.getMessage(), exception);
		}
		return template;
	}


}

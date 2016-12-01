package hu.zrs.lbs.translator.gradle.template;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class ClasspathTemplateProvider implements TemplateProvider<Template> {

	private final VelocityEngine velocityEngine;

	private final String templateDirectory;

	public ClasspathTemplateProvider(String templateDirectory) {
		this.templateDirectory = templateDirectory;
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

	}

	@Override
	public Template getTemplate(String templateName) {
		Template template = null;
		try {
			template = velocityEngine.getTemplate(templateDirectory + templateName);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return template;
	}


}

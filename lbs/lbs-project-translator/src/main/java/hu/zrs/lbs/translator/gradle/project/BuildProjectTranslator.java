package hu.zrs.lbs.translator.gradle.project;

import hu.zrs.lbs.api.project.BuildProject;
import hu.zrs.lbs.api.translator.Translator;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilder;
import hu.zrs.lbs.translator.gradle.template.TemplateBuilderFactory;

public class BuildProjectTranslator implements Translator<BuildProject> {

	private final TemplateBuilderFactory templateBuilderFactory;

	public BuildProjectTranslator(TemplateBuilderFactory templateBuilderFactory) {
		this.templateBuilderFactory = templateBuilderFactory;
	}

	@Override
	public String translate(BuildProject buildProject) {
		final TemplateBuilder projectTemplateBuilder = templateBuilderFactory.getBuilder("GradleProject.gtpl");
		projectTemplateBuilder.addParameter("project", buildProject);
		return projectTemplateBuilder.getContent();
	}

}

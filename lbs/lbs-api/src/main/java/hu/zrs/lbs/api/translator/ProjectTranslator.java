package hu.zrs.lbs.api.translator;

import hu.zrs.lbs.api.project.Project;

public interface ProjectTranslator extends Translator<Project> {

	@Override
	String translate(Project project);

}

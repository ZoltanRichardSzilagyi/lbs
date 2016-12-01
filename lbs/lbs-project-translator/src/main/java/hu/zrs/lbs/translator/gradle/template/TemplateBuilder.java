package hu.zrs.lbs.translator.gradle.template;

public interface TemplateBuilder {

	void addParameter(String name, Object parameter);

	String getContent();

}
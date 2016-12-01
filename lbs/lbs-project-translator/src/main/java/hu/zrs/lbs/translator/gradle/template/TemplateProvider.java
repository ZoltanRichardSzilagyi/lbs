package hu.zrs.lbs.translator.gradle.template;

public interface TemplateProvider<T> {

	T getTemplate(String templateName);

}

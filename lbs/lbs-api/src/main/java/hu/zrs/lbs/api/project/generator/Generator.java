package hu.zrs.lbs.api.project.generator;

public interface Generator<I, C> {

	void generate(I item, C generatorContext);

}

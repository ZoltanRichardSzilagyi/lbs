package hu.zrs.lbs.api.translator;

import hu.zrs.lbs.api.step.Step;

public interface StepTranslator extends Translator<Step> {

	@Override
	String translate(Step step);

}

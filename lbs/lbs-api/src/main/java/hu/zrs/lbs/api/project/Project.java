package hu.zrs.lbs.api.project;

import java.util.List;

import hu.zrs.lbs.api.step.Step;
import hu.zrs.lbs.api.translator.Translatable;

public interface Project extends Translatable {

	String getName();

	List<Step> getSteps();

}

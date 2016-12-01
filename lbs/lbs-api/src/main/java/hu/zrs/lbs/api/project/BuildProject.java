package hu.zrs.lbs.api.project;

import java.util.List;

import hu.zrs.lbs.api.step.Step;

public class BuildProject implements Project {

	private final String name;

	private final List<Step> steps;

	public BuildProject(final String name, final List<Step> steps) {
		this.name = name;
		this.steps = steps;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Step> getSteps() {
		return steps;
	}

}

package hu.zrs.lbs.api.project;

import java.util.List;

import hu.zrs.lbs.api.step.Step;

public class BuildProject implements Project {

	private final Integer id;

	private String name;

	private List<Step> steps;

	public BuildProject(final Integer id) {
		this.id = id;
	}

	public BuildProject(final Integer id, final String name) {
		this.id = id;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(final List<Step> steps) {
		this.steps = steps;
	}

}

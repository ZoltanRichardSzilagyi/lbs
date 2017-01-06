package hu.zrs.lbs.task.build;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.task.TaskAttribute;

public class Copy implements Task {

	private String name;
	@TaskAttribute
	private String from;
	@TaskAttribute
	private String into;
	@TaskAttribute
	private String include;

	public Copy() {
	}

	public Copy(final String name, final String from, final String into, final String include) {
		this.name = name;
		this.from = from;
		this.into = into;
		this.include = include;
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(final String from) {
		this.from = from;
	}

	public String getInto() {
		return into;
	}

	public void setInto(final String into) {
		this.into = into;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(final String include) {
		this.include = include;
	}

}

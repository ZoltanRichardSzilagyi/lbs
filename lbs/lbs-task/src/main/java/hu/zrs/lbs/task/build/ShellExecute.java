package hu.zrs.lbs.task.build;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.task.TaskAttribute;

public class ShellExecute implements Task {

	public ShellExecute() {
	}

	public ShellExecute(final String name, final String shellCommand) {
		this.name = name;
		this.shellCommand = shellCommand;
	}

	private String name;

	@TaskAttribute
	private String shellCommand;


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

	public String getShellCommand() {
		return shellCommand;
	}

	public void setShellCommand(final String shellCommand) {
		this.shellCommand = shellCommand;
	}

}

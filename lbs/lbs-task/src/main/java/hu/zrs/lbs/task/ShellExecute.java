package hu.zrs.lbs.task;

import hu.zrs.lbs.api.task.Task;

public class ShellExecute implements Task {

	public ShellExecute() {
	}

	public ShellExecute(final String name, final String shellCommand) {
		this.name = name;
		this.shellCommand = shellCommand;
	}

	private String name;

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

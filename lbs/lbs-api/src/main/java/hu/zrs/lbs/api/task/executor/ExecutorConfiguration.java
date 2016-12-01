package hu.zrs.lbs.api.task.executor;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class ExecutorConfiguration {

	private final List<String> tasks;

	private final OutputStream standarOutput;

	private final OutputStream errorOutput;

	public ExecutorConfiguration(final List<String> tasks, final OutputStream standardOutput, final OutputStream errorOutput) {
		this.tasks = tasks;
		this.standarOutput = standardOutput;
		this.errorOutput = errorOutput;
	}

	public ExecutorConfiguration(final String task, final OutputStream standardOutput, final OutputStream errorOutput) {
		this.tasks = Arrays.asList(task);
		this.standarOutput = standardOutput;
		this.errorOutput = errorOutput;
	}

	public List<String> getTasks() {
		return tasks;
	}

	public OutputStream getStandarOutput() {
		return standarOutput;
	}

	public OutputStream getErrorOutput() {
		return errorOutput;
	}

}

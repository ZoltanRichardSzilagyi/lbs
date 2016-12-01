package hu.zrs.lbs.agent.tasks;

import hu.zrs.lbs.api.task.Task;

public class FooTask implements Task {

	@Override
	public String getType() {
		return FooTask.class.getSimpleName();
	}

	@Override
	public String getName() {
		return "FooTask";
	}

	@Override
	public void setName(final String name) {
		// TODO Auto-generated method stub

	}

}

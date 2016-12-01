package hu.zrs.lbs.agent.tasks;

import hu.zrs.lbs.api.task.Task;

public class BarTask implements Task {

	@Override
	public String getType() {
		return BarTask.class.getSimpleName();
	}

	@Override
	public String getName() {
		return "BarTask";
	}

	@Override
	public void setName(final String name) {
		// TODO Auto-generated method stub

	}

}

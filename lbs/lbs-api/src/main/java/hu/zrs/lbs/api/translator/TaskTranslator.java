package hu.zrs.lbs.api.translator;

import hu.zrs.lbs.api.task.Task;

public interface TaskTranslator extends Translator<Task> {

	@Override
	String translate(Task command);

}

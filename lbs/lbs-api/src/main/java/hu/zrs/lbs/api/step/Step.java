package hu.zrs.lbs.api.step;

import java.util.List;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.translator.Translatable;

public interface Step extends Translatable {

	String getName();

	List<Task> getTasks();

}

package hu.zrs.lbs.api.task;

import hu.zrs.lbs.api.translator.Translatable;

// TODO task rules!!!
public interface Task extends Translatable {

	String getType();

	String getName();

	void setName(String name);

}

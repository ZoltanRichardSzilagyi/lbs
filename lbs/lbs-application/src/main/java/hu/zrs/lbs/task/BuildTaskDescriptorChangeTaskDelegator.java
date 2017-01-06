package hu.zrs.lbs.task;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

public class BuildTaskDescriptorChangeTaskDelegator implements CustomTaskChange {

	private static CustomTaskChange DELEGATED_CUSTOM_TASK_CHANGE;

	public static synchronized void setDelegatedChangeTask(final CustomTaskChange customTaskChange) {
		if (DELEGATED_CUSTOM_TASK_CHANGE == null) {
			DELEGATED_CUSTOM_TASK_CHANGE = customTaskChange;
		}
	}

	@Override
	public String getConfirmationMessage() {
		return DELEGATED_CUSTOM_TASK_CHANGE.getConfirmationMessage();
	}

	@Override
	public void setUp() throws SetupException {
		DELEGATED_CUSTOM_TASK_CHANGE.setUp();
	}

	@Override
	public void setFileOpener(final ResourceAccessor resourceAccessor) {
		DELEGATED_CUSTOM_TASK_CHANGE.setFileOpener(resourceAccessor);
	}

	@Override
	public ValidationErrors validate(final Database database) {
		return DELEGATED_CUSTOM_TASK_CHANGE.validate(database);
	}

	@Override
	public void execute(final Database database) throws CustomChangeException {
		DELEGATED_CUSTOM_TASK_CHANGE.execute(database);
	}

}

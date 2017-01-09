package hu.zrs.lbs.task;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

public class BuildTaskDescriptorChangeTaskDelegator implements CustomTaskChange {

	private static CustomTaskChange delegatedCustomTaskChange;

	public static synchronized void setDelegatedChangeTask(final CustomTaskChange customTaskChange) {
		if (delegatedCustomTaskChange == null) {
			delegatedCustomTaskChange = customTaskChange;
		}
	}

	@Override
	public String getConfirmationMessage() {
		return delegatedCustomTaskChange.getConfirmationMessage();
	}

	@Override
	public void setUp() throws SetupException {
		delegatedCustomTaskChange.setUp();
	}

	@Override
	public void setFileOpener(final ResourceAccessor resourceAccessor) {
		delegatedCustomTaskChange.setFileOpener(resourceAccessor);
	}

	@Override
	public ValidationErrors validate(final Database database) {
		return delegatedCustomTaskChange.validate(database);
	}

	@Override
	public void execute(final Database database) throws CustomChangeException {
		delegatedCustomTaskChange.execute(database);
	}

}

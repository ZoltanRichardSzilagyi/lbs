package hu.zrs.lbs.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hu.zrs.lbs.api.task.TaskDescriptorService;
import hu.zrs.lbs.task.change.TaskTypeDescriptorChangeLogGenerator;
import liquibase.change.custom.CustomTaskChange;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

public class BuildTaskDescriptorTaskChange implements CustomTaskChange {

	private static final Logger logger = LoggerFactory.getLogger(BuildTaskDescriptorTaskChange.class);

	@Autowired
	private TaskDescriptorService taskDescriptorService;

	@Autowired
	private TaskTypeDescriptorChangeLogGenerator changeLogGenerator;

	@Override
	public String getConfirmationMessage() {
		return null;
	}

	@Override
	public void setUp() throws SetupException {

	}

	@Override
	public void setFileOpener(final ResourceAccessor resourceAccessor) {

	}

	@Override
	public ValidationErrors validate(final Database database) {
		return null;
	}

	@Override
	public void execute(final Database database) throws CustomChangeException {
		DatabaseChangeLog taskDescriptorChangeLog = changeLogGenerator.generate();
		taskDescriptorChangeLog.getChangeSets().forEach(changeSet -> {
			try {
				changeSet.execute(taskDescriptorChangeLog, database);
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				throw new RuntimeException(exception);
			}
		});
	}


}

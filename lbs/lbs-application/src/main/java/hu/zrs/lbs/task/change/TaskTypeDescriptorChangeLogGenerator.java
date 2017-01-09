package hu.zrs.lbs.task.change;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import hu.zrs.lbs.api.task.TaskAttributeDescriptor;
import hu.zrs.lbs.api.task.TaskDescriptor;
import hu.zrs.lbs.api.task.TaskDescriptorService;
import hu.zrs.lbs.task.InMemoryResourceAccessor;
import hu.zrs.lbs.task.csv.CSVSerializedTaskDescriptor;
import hu.zrs.lbs.task.csv.CSVTaskAttributeDescriptorSerializer;
import hu.zrs.lbs.task.csv.CSVTaskTypeDescriptorSerializer;
import liquibase.change.core.LoadDataColumnConfig;
import liquibase.change.core.LoadUpdateDataChange;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.exception.LiquibaseException;

@Component
public class TaskTypeDescriptorChangeLogGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskTypeDescriptorChangeLogGenerator.class);

	@Value("${csv.separator}")
	private String separator;

	@Value("${csv.quotechar}")
	private String quoteChar;

	@Autowired
	private TaskDescriptorService taskDescriptorService;
	
	@Autowired
	private CSVTaskTypeDescriptorSerializer taskDescriptorSerilaizer;

	@Autowired
	private CSVTaskAttributeDescriptorSerializer taskAttributeDescriptorSerializer;

	public DatabaseChangeLog generate() {
		Collection<TaskDescriptor> taskDescriptors = taskDescriptorService.getTaskDescriptors();

		List<TaskAttributeDescriptor> taskAttributeDescriptors = new ArrayList<>();

		taskDescriptors.forEach(taskDescriptor -> taskAttributeDescriptors.addAll(taskDescriptor.getAttributeIdentifiers()));

		DatabaseChangeLog databaseChangeLog = new DatabaseChangeLog();

		CSVSerializedTaskDescriptor csvTaskDescriptor = taskDescriptorSerilaizer.serialize(taskDescriptors);
		createChangeSet(csvTaskDescriptor, databaseChangeLog, "task_type");
		CSVSerializedTaskDescriptor csvTaskAttributeDescriptor = taskAttributeDescriptorSerializer.serialize(taskAttributeDescriptors);
		createChangeSet(csvTaskAttributeDescriptor, databaseChangeLog, "task_type_attribute");

		return databaseChangeLog;
	}

	private void createChangeSet(final CSVSerializedTaskDescriptor csvTaskDescriptor, final DatabaseChangeLog databaseChangeLog, final String tableName) {
		ChangeSet taskDescriptorChangeSet = initializeChangeset(databaseChangeLog, tableName);
		databaseChangeLog.addChangeSet(taskDescriptorChangeSet);
		LoadUpdateDataChange taskDescriptorChange = createTaskDescriptorChangeSet(tableName, csvTaskDescriptor);
		taskDescriptorChangeSet.addChange(taskDescriptorChange);
		taskDescriptorChange.setChangeSet(taskDescriptorChangeSet);
	}

	private LoadUpdateDataChange createTaskDescriptorChangeSet(final String tableName, final CSVSerializedTaskDescriptor csvTaskDescriptor) {
		LoadUpdateDataChange loadUpdateChange = new LoadUpdateDataChange();
		loadUpdateChange.setTableName(tableName);
		loadUpdateChange.setResourceAccessor(new InMemoryResourceAccessor(csvTaskDescriptor.getStream()));

		csvTaskDescriptor.getFields().forEach(field -> {
			LoadDataColumnConfig column = new LoadDataColumnConfig();
			column.setName(field);
			column.setHeader(field);
			column.setType("string");
			loadUpdateChange.addColumn(column);
		});
		loadUpdateChange.setSeparator(separator);
		loadUpdateChange.setQuotchar(quoteChar);
		// TODO do not use spring stringutils
		String primaryKey = StringUtils.collectionToCommaDelimitedString(csvTaskDescriptor.getFields());
		try {
			loadUpdateChange.setPrimaryKey(primaryKey);
		} catch (LiquibaseException exception) {
			logger.error(exception.getMessage(), exception);
		}
		return loadUpdateChange;
	}

	private ChangeSet initializeChangeset(final DatabaseChangeLog databaseChangeLog, final String tableName) {
		return new ChangeSet(tableName + "-load-or-update", "application", false, true, null, null, null, true, null, databaseChangeLog);
	}


}

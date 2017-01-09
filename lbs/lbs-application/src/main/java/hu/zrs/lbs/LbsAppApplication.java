package hu.zrs.lbs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonProcessingException;

import hu.zrs.lbs.api.task.TaskTypeMapFactory;
import hu.zrs.lbs.project.executor.BuildProjectCommandExecutor;
import hu.zrs.lbs.task.BuildTaskDescriptorChangeTaskDelegator;
import hu.zrs.lbs.task.BuildTaskDescriptorTaskChange;
import liquibase.integration.spring.SpringLiquibase;

@SpringBootApplication

public class LbsAppApplication {

	public static void main(final String[] args) throws JsonProcessingException {
		SpringApplication.run(LbsAppApplication.class, args);
	}

	@Bean
	public BuildTaskDescriptorTaskChange taskDescriptorChange() {
		return new BuildTaskDescriptorTaskChange();
	}

	@Bean(name = { "liquibase" })
	public SpringLiquibase getLiquibase(@Autowired final DataSource dataSource, @Autowired final BuildTaskDescriptorTaskChange taskDescriptorTaskChange) {
		BuildTaskDescriptorChangeTaskDelegator.setDelegatedChangeTask(taskDescriptorTaskChange);
		final SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog("classpath:db/db-changelog.xml");
		return springLiquibase;
	}

	@Bean
	public CommandLineRunner runner(@Autowired final BuildProjectCommandExecutor buildProjectCommandExecutor) {
		return args -> buildProjectCommandExecutor.startExecution();
	}

	@Bean
	TaskTypeMapFactory taskTypeMapFactory() {
		return new TaskTypeMapFactory();
	}


}

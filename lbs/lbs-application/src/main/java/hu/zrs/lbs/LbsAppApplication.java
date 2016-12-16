package hu.zrs.lbs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hu.zrs.lbs.project.executor.BuildProjectCommandExecutor;
import liquibase.integration.spring.SpringLiquibase;

@SpringBootApplication

public class LbsAppApplication {

	public static void main(final String[] args) {
		SpringApplication.run(LbsAppApplication.class, args);
	}

	@Bean(name = { "liquibase" })
	public SpringLiquibase getLiquibase(@Autowired final DataSource dataSource) {
		final SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog("classpath:db/db-changelog.xml");
		return springLiquibase;
	}

	@Bean
	public CommandLineRunner runner(@Autowired final BuildProjectCommandExecutor buildProjectCommandExecutor) {
		return args -> {
			buildProjectCommandExecutor.startExecution();
		};
	}

}

package hu.zrs.lbs.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import hu.zrs.lbs.agent.client.AgentRegistryClient;


@SpringBootApplication
public class LbsAgentApplication {


	public static void main(final String[] args) {
		final ConfigurableApplicationContext applicationContext = SpringApplication.run(LbsAgentApplication.class, args);
		applicationContext.close();
	}

	@Bean
	public CommandLineRunner runner(@Autowired final AgentRegistryClient agentClient) {
		return args -> {
			agentClient.registerAgent();
		};
	}

}

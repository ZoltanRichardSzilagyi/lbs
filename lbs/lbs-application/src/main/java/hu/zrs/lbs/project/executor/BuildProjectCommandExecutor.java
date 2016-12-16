package hu.zrs.lbs.project.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildProjectCommandExecutor {

	private static final int NUMBER_OF_WORKER_THREADS = 4;

	private final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_WORKER_THREADS);

	@Autowired
	private BuildProjectCommandExecutorTaskFactory taskFactory;

	public void startExecution() {
		for (int i = 0; i < NUMBER_OF_WORKER_THREADS; i++) {
			executor.submit(taskFactory.createTask());
		}
	}

}

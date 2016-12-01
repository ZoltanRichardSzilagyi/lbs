package hu.zrs.lbs.api.task.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutionLog {
	
	private final List<String> executionLog = new ArrayList<>();
	
	private final List<Throwable> executionErrors = new ArrayList<>();
	
	public ExecutionLog() {
	}
	
	public ExecutionLog(ExecutionLog resultToMerge){
		executionLog.addAll(resultToMerge.getExecutionLog());
		executionErrors.addAll(resultToMerge.getExecutionErrors());
	}

	public void addExecutionLog(String logMessage){
		executionLog.add(logMessage);
	}
	
	public void addExecutionErrorLog(Throwable executionError){
		executionErrors.add(executionError);
	}
	
	public boolean isExecutionSuccess(){
		return executionErrors.isEmpty();
	}

	public List<String> getExecutionLog() {
		return executionLog;
	}

	public List<Throwable> getExecutionErrors() {
		return executionErrors;
	}

}

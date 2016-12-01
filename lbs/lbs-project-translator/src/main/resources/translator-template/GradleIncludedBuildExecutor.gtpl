// refactor imports
import hu.zrs.lbs.api.task.executor.ExecutorConfiguration
import hu.zrs.lbs.task.executor.gradle.GradleToolingTaskExecutor
import hu.zrs.lbs.api.task.executor.ExecutionLog
// TODO fix error handling and set outputstream of tooling executor
task ${task.name} << {
	File workingDirectoryPath = new File(rootProject.projectDir, "workingDirectory")
	File includedProjectPath = new File(workingDirectoryPath, "$task.includedBuild")
	GradleToolingTaskExecutor executor = new GradleToolingTaskExecutor(includedProjectPath)
	def tasksToExecute = [#foreach($taskToExecute in $task.tasksToExecute)'$taskToExecute'#if($foreach.hasNext), #end#end]
	
	ExecutorConfiguration configuration = new ExecutorConfiguration(tasksToExecute, System.out, System.err);	

	ExecutionLog executionLog = executor.execute(configuration)
	executionLog.executionLog.each{
		String logMessage ->
		println logMessage
	}
	if (!executionLog.isExecutionSuccess()) { 
		throw new StopExecutionException()
	}
	
}

task ${task.name}(type: Exec){
	environment("PATH", System.getenv("PATH"))
	workingDir project.projectDir
	commandLine '${task.shellCommand}'
}
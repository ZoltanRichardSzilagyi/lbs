task ${task.name}(dependsOn: [#foreach($taskToExecute in $task.tasksToExecute)'$taskToExecute'#if($foreach.hasNext), #end#end]) << {}
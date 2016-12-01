${tasks}
#set ($previousTaskName=false)
#foreach($task in $step.tasks)
#if($previousTaskName)
${task.name}.mustRunAfter ${previousTaskName}
#end
#set ($previousTaskName = $task.name) 
#end
task executeStep(dependsOn: [#foreach($task in $step.tasks)'$task.name'#if($foreach.hasNext), #end#end]){
}
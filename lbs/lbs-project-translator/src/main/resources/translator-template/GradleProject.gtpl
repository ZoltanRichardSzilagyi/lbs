buildscript{
	repositories{
		mavenLocal()
		mavenCentral()
	}
	dependencies {
		// refactor this solution to do not depend on any repository
		classpath 'hu.zrs.lbs:lbs-api:1.0.0'
		classpath 'hu.zrs.lbs:lbs-gradle-tooling-executor:1.0.0'
	}
}

#set ($previousStepName=false)
#foreach($step in $project.steps)
#if($previousStepName)
task('${step.name}:executeStep').mustRunAfter(':${previousStepName}:executeStep')
#end
#set ($previousStepName = $step.name) 
#end

task executeProject(dependsOn: [#foreach($step in $project.steps)':$step.name:executeStep'#if($foreach.hasNext), #end#end]){
}
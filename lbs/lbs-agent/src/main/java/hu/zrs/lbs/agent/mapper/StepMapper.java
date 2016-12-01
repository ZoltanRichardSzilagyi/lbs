package hu.zrs.lbs.agent.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.step.BuildStep;
import hu.zrs.lbs.api.step.Step;
import hu.zrs.lbs.api.task.Task;

@Component
public class StepMapper implements EntityMapper<List<hu.zrs.lbs.entity.persistence.Step>, List<Step>> {

	@Autowired
	private EntityMapper<List<hu.zrs.lbs.entity.persistence.Task>, List<Task>> taskMapper;

	@Override
	public List<Step> map(final List<hu.zrs.lbs.entity.persistence.Step> steps) {
		return steps.stream().map(step -> {
			final List<Task> buildTasks = taskMapper.map(step.getTasks());
			return new BuildStep(step.getName(), buildTasks);
		}).collect(Collectors.toList());
	}

}
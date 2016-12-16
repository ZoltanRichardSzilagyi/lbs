package hu.zrs.lbs.agent.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.zrs.lbs.api.project.BuildProject;
import hu.zrs.lbs.api.project.Project;
import hu.zrs.lbs.entity.persistence.Step;

@Component
public class ProjectMapper implements EntityMapper<hu.zrs.lbs.entity.persistence.Project, Project> {

	@Autowired
	private EntityMapper<List<Step>, List<hu.zrs.lbs.api.step.Step>> stepMapper;

	@Override
	public Project map(final hu.zrs.lbs.entity.persistence.Project project) {
		final List<hu.zrs.lbs.api.step.Step> buildSteps = stepMapper.map(project.getSteps());
		final BuildProject buildProject = new BuildProject(project.getId(), project.getName());
		buildProject.setSteps(buildSteps);
		return buildProject;
	}

}

package hu.zrs.lbs.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.zrs.lbs.entity.persistence.Project;
import hu.zrs.lbs.repository.ProjectRepository;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectRepository projectRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Project> getProjects() {
		return projectRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void saveProject(@RequestBody final Project project) {

	}

}

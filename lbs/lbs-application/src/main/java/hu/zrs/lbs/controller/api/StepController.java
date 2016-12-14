package hu.zrs.lbs.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.zrs.lbs.entity.persistence.Project;
import hu.zrs.lbs.entity.persistence.Step;
import hu.zrs.lbs.repository.StepRepository;

@RestController
@RequestMapping("/api/project/{projectId}/step")
public class StepController {

	@Autowired
	private StepRepository stepRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Step> findAllByProject(@PathVariable final Integer projectId) {
		return stepRepository.findAllByProject(new Project(projectId));
	}

}

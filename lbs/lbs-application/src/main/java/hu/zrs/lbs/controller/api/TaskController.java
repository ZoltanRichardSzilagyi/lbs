package hu.zrs.lbs.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.zrs.lbs.entity.persistence.Step;
import hu.zrs.lbs.entity.persistence.Task;
import hu.zrs.lbs.repository.TaskRepository;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Task> getTasksOfProject(@RequestParam final Integer stepId) {
		return taskRepository.findAllByStep(new Step(stepId));
	}

}

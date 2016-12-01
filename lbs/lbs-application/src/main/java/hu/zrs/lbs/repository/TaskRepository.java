package hu.zrs.lbs.repository;

import org.springframework.data.repository.CrudRepository;

import hu.zrs.lbs.entity.persistence.Step;
import hu.zrs.lbs.entity.persistence.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {

	Iterable<Task> findAllByStep(Step project);

}

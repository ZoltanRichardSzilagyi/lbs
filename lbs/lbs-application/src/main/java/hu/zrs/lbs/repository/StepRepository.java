package hu.zrs.lbs.repository;

import org.springframework.data.repository.CrudRepository;

import hu.zrs.lbs.entity.persistence.Project;
import hu.zrs.lbs.entity.persistence.Step;

public interface StepRepository extends CrudRepository<Step, Integer> {


	public Iterable<Step> findAllByProject(Project projects);

}

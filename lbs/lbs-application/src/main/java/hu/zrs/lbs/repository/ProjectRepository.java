package hu.zrs.lbs.repository;

import org.springframework.data.repository.CrudRepository;

import hu.zrs.lbs.entity.persistence.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

}

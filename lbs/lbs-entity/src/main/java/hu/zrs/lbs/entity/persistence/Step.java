package hu.zrs.lbs.entity.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Step implements Serializable{

	private static final long serialVersionUID = -784154857046673353L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String description;

	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonIgnore
	private Project project;

	@OneToMany
	@JsonIgnore
	private List<Task> tasks;


	public Step() {
	}

	public Step(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(final List<Task> tasks) {
		this.tasks = tasks;
	}

}

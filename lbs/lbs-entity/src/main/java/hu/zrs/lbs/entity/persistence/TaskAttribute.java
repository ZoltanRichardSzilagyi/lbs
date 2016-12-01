package hu.zrs.lbs.entity.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TaskAttribute implements Serializable{

	private static final long serialVersionUID = 1567131037326915688L;


	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "task_id")
	@JsonIgnore
	private Task task;

	private String name;

	private String value;

	public TaskAttribute() {
	}

	public TaskAttribute(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(final Task task) {
		this.task = task;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}
	
	

}

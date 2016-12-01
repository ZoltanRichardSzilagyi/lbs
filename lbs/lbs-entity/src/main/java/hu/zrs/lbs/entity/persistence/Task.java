package hu.zrs.lbs.entity.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task implements Serializable{

	private static final long serialVersionUID = 3219175247822846014L;

	@Id
	@GeneratedValue
	private Integer id;

	private String type;

	private String name;

	@OneToMany
	@JsonIgnore
	private List<TaskAttribute> attributes;

	@ManyToOne
	private Step step;

	public Task() {

	}

	public Task(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<TaskAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(final List<TaskAttribute> attributes) {
		this.attributes = attributes;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(final Step step) {
		this.step = step;
	}

}

package hu.zrs.lbs.entity.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project implements Serializable{

	private static final long serialVersionUID = 2509003002902161131L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "project")
	private List<Step> steps;

	public Project() {
	}

	public Project(final Integer id) {
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

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(final List<Step> steps) {
		this.steps = steps;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

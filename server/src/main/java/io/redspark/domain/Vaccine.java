package io.redspark.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Bean responsavel pelas vacinas
 * 
 * @author massao
 */
@Entity
public class Vaccine {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String name;

	@OneToMany(mappedBy = "vaccine", fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH, CascadeType.REMOVE })
	private List<VaccineSchedule> vaccineSchedule;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VaccineSchedule> getVaccineSchedule() {
		return vaccineSchedule;
	}

	public void setVaccineSchedule(List<VaccineSchedule> vaccineSchedule) {
		this.vaccineSchedule = vaccineSchedule;
	}

}

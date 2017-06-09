package io.redspark.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Bean de agenda de vacinas
 * 
 * @author massao
 */
@Entity
public class VaccineSchedule extends Schedule {

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, optional = true)
	private Vaccine vaccine;

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, optional = true)
	private Animal animal;

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}

package io.redspark.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * bean que representa a agenda do veterinario
 * 
 * @author massao
 */
@Entity
@Table(name = "consulta_veterinario")
public class VeterinarySchedule extends Schedule {

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, optional = true)
	private Animal animal;

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, optional = true)
	private Veterinary veterinary;

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Veterinary getVeterinary() {
		return veterinary;
	}

	public void setVeterinary(Veterinary veterinary) {
		this.veterinary = veterinary;
	}
}

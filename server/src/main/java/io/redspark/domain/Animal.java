package io.redspark.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Bean que representa os animais
 * 
 * @author massao
 */
@Entity
public class Animal {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String name;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Owner owner;

	@NotNull
	private String specie;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "animal", cascade = { CascadeType.REFRESH, CascadeType.REMOVE })
	private List<VaccineSchedule> vaccineSchedule;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "animal", cascade = { CascadeType.REFRESH, CascadeType.REMOVE })
	private List<VeterinarySchedule> veterinarySchedule;

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

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}
}

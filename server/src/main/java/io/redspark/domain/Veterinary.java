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
 * bean que representa o veterinario
 * 
 * @author massao
 */
@Entity
public class Veterinary {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "veterinary", cascade = { CascadeType.REFRESH, CascadeType.REMOVE })
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
}

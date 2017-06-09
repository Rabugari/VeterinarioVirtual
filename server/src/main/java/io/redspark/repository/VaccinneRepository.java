package io.redspark.repository;

import org.springframework.data.repository.CrudRepository;

import io.redspark.domain.Vaccine;

public interface VaccinneRepository extends CrudRepository<Vaccine, Long> {

	Vaccine findOneByNameIgnoreCase(String name);

}

package io.redspark.repository;

import org.springframework.data.repository.CrudRepository;

import io.redspark.domain.Veterinary;


public interface VeterinaryRepository extends CrudRepository<Veterinary, Long>{

	Veterinary findOneByNameIgnoreCase(String name);
}

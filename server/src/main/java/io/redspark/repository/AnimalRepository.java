package io.redspark.repository;

import org.springframework.data.repository.CrudRepository;

import io.redspark.domain.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

}

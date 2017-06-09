package io.redspark.repository;

import org.springframework.data.repository.CrudRepository;

import io.redspark.domain.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>{

}

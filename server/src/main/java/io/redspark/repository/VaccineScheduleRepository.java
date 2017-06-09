package io.redspark.repository;

import org.springframework.data.repository.CrudRepository;

import io.redspark.domain.VaccineSchedule;

public interface VaccineScheduleRepository extends CrudRepository<VaccineSchedule, Long> {

}

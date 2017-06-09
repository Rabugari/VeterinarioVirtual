package io.redspark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.redspark.domain.VaccineSchedule;
import io.redspark.domain.VeterinarySchedule;
import io.redspark.repository.VaccineScheduleRepository;
import io.redspark.repository.VeterinaryScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private VaccineScheduleRepository vaccineScheduleRepository;

	@Autowired
	private VeterinaryScheduleRepository veterinaryScheduleRepository;

	/** Para agendamento de vacinas */
	public List<VaccineSchedule> findAllVaccineSchedule() {
		return (List<VaccineSchedule>) vaccineScheduleRepository.findAll();
	}

	public VaccineSchedule schedule(final VaccineSchedule vaccineSchedule) {
		return vaccineScheduleRepository.save(vaccineSchedule);
	}

	public VaccineSchedule findOneVaccineSchedule(final Long id) {
		return vaccineScheduleRepository.findOne(id);
	}

	public void deleteVaccineSchedule(final Long id) {
		vaccineScheduleRepository.delete(id);
	}

	/** Para agendamento com o veterinario */
	public List<VeterinarySchedule> findAllVeterinarySchedule() {
		return (List<VeterinarySchedule>) veterinaryScheduleRepository.findAll();
	}

	public VeterinarySchedule schedule(VeterinarySchedule vaccineSchedule) {
		return veterinaryScheduleRepository.save(vaccineSchedule);
	}

	public VeterinarySchedule findOneVeterinarySchedule(Long id) {
		return veterinaryScheduleRepository.findOne(id);
	}

	public void deleteVeterinarySchedule(Long id) {
		veterinaryScheduleRepository.delete(id);
	}
}

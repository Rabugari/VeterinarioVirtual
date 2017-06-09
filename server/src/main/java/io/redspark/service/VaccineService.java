package io.redspark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.redspark.domain.Vaccine;
import io.redspark.repository.VaccinneRepository;

@Service
public class VaccineService {

	@Autowired
	private VaccinneRepository vacinaRepository;

	public List<Vaccine> findAll() {
		return (List<Vaccine>) vacinaRepository.findAll();
	}

	public boolean salvar(final Vaccine vaccine) {
		final Vaccine bdVaccine = vacinaRepository.findOneByNameIgnoreCase(vaccine.getName());
		if (bdVaccine == null) {
			vacinaRepository.save(vaccine);
			return true;
		}
		return false;
	}

	public Vaccine findOne(final Long id) {
		return vacinaRepository.findOne(id);
	}

	public void delete(final Long id) {
		vacinaRepository.delete(id);
	}

}

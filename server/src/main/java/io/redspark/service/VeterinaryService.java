package io.redspark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.redspark.domain.Veterinary;
import io.redspark.repository.VeterinaryRepository;

@Service
public class VeterinaryService {

	@Autowired
	private VeterinaryRepository veterinaryRepository;

	public boolean save(Veterinary veterinary) {
		final Veterinary bdVeterinary = veterinaryRepository.findOneByNameIgnoreCase(veterinary.getName());
		if (bdVeterinary == null) {
			veterinary = veterinaryRepository.save(veterinary);
			return true;
		}
		return false;
	}

	public List<Veterinary> findAll() {
		return (List<Veterinary>) veterinaryRepository.findAll();
	}

	public Veterinary findOne(final Long id) {
		return veterinaryRepository.findOne(id);
	}

	public void delete(final Long id) {
		veterinaryRepository.delete(id);
	}

}

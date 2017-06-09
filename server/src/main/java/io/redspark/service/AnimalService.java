package io.redspark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.redspark.domain.Animal;
import io.redspark.domain.Owner;
import io.redspark.repository.AnimalRepository;
import io.redspark.repository.OwnerRepository;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	public List<Animal> findAll() {
		return (List<Animal>) animalRepository.findAll();
	}

	public Animal save(final Animal animal) {
		final Owner owner = ownerRepository.save(animal.getOwner());
		animal.setOwner(owner);
		final Animal newAnimal = animalRepository.save(animal);
		return newAnimal;
	}

	public Animal findOne(Long id) {
		return animalRepository.findOne(id);
	}

	public void delete(Long id) {
		animalRepository.delete(id);
	}
}

package com.animalnovels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animalnovels.model.Animal;
import com.animalnovels.repository.AnimalRepository;

@Service
public class AnimalService {
    
    @Autowired
    private AnimalRepository animalRepository;
    
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }
    
    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }
    
    public List<Animal> findBySpecies(String species) {
        return animalRepository.findBySpecies(species);
    }
    
    public List<Animal> findByNameContaining(String name) {
        return animalRepository.findByAnameContaining(name);
    }
    
    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }
    
    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }
}

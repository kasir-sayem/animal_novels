package com.animalnovels.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animalnovels.model.Animal;
import com.animalnovels.model.Novel;
import com.animalnovels.service.AnimalService;
import com.animalnovels.service.NovelService;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private AnimalService animalService;
    
    @Autowired
    private NovelService novelService;
    
    
    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return new ResponseEntity<>(animalService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/animals/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id) {
        Optional<Animal> animal = animalService.findById(id);
        return animal.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/animals/species/{species}")
    public ResponseEntity<List<Animal>> getAnimalsBySpecies(@PathVariable String species) {
        return new ResponseEntity<>(animalService.findBySpecies(species), HttpStatus.OK);
    }
    
    // Novel API endpoints
    @GetMapping("/novels")
    public ResponseEntity<List<Novel>> getAllNovels() {
        return new ResponseEntity<>(novelService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/novels/{id}")
    public ResponseEntity<Novel> getNovel(@PathVariable Long id) {
        Optional<Novel> novel = novelService.findById(id);
        return novel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/novels/year/{startYear}/{endYear}")
    public ResponseEntity<List<Novel>> getNovelsByYearRange(
            @PathVariable Integer startYear, 
            @PathVariable Integer endYear) {
        return new ResponseEntity<>(
                novelService.findByYearRange(startYear, endYear), 
                HttpStatus.OK);
    }
}

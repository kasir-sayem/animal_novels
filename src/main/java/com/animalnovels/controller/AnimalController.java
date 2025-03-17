package com.animalnovels.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.animalnovels.model.Animal;
import com.animalnovels.service.AnimalService;

@Controller
@RequestMapping("/animals")
public class AnimalController {
    
    @Autowired
    private AnimalService animalService;
    
    @GetMapping
    public String listAnimals(Model model, @RequestParam(required = false) String species) {
        List<Animal> animals;
        
        if (species != null && !species.isEmpty()) {
            animals = animalService.findBySpecies(species);
        } else {
            animals = animalService.findAll();
        }
        
        model.addAttribute("animals", animals);
        return "animals/list";
    }
    
    @GetMapping("/{id}")
    public String viewAnimal(@PathVariable Long id, Model model) {
        Optional<Animal> animal = animalService.findById(id);
        
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "animals/view";
        } else {
            return "redirect:/animals";
        }
    }
}

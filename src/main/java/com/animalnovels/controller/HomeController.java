package com.animalnovels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.animalnovels.model.Animal;
import com.animalnovels.model.Novel;
import com.animalnovels.service.AnimalService;
import com.animalnovels.service.NovelService;

@Controller
public class HomeController {
    
    @Autowired
    private AnimalService animalService;
    
    @Autowired
    private NovelService novelService;
    
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        List<Animal> featuredAnimals = animalService.findAll().subList(0, Math.min(4, animalService.findAll().size()));
        List<Novel> recentNovels = novelService.findAll().subList(0, Math.min(3, novelService.findAll().size()));
        
        model.addAttribute("featuredAnimals", featuredAnimals);
        model.addAttribute("recentNovels", recentNovels);
        
        return "home";
    }
    
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
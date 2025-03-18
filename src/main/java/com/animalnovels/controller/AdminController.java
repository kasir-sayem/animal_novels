package com.animalnovels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.animalnovels.service.UserService;
// Import other services as needed

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    // Autowire other services as needed
    // private AnimalService animalService;
    // private NovelService novelService;
    // private MessageService messageService;
    
    @GetMapping
    public String adminDashboard() {
        return "admin";
    }
    
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }
    
    @GetMapping("/animals")
    public String manageAnimals(Model model) {
        // Uncomment when you have the service
        // model.addAttribute("animals", animalService.findAll());
        return "admin/animals";
    }
    
    @GetMapping("/novels")
    public String manageNovels(Model model) {
        // Uncomment when you have the service
        // model.addAttribute("novels", novelService.findAll());
        return "admin/novels";
    }
    
    @GetMapping("/messages")
    public String viewMessages(Model model) {
        // Uncomment when you have the service
        // model.addAttribute("messages", messageService.findAll());
        return "admin/messages";
    }
    
    @GetMapping("/api-docs")
    public String apiDocs() {
        return "api-docs";
    }
}
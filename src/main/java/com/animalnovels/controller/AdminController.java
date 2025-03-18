package com.animalnovels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.animalnovels.model.Animal;
import com.animalnovels.model.Message;
import com.animalnovels.model.Novel;
import com.animalnovels.model.User;
import com.animalnovels.service.AnimalService;
import com.animalnovels.service.MessageService;
import com.animalnovels.service.NovelService;
import com.animalnovels.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AnimalService animalService;
    
    @Autowired
    private NovelService novelService;
    
    @Autowired
    private MessageService messageService;
    
    // Main Dashboard
    @GetMapping
    public String adminDashboard() {
        return "admin";
    }
    
    // API Documentation
    @GetMapping("/api-docs")
    public String apiDocs() {
        return "api-docs";
    }
    
    // ===== USER MANAGEMENT =====
    
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }
    
    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "admin/user-edit";
    }
    
    @PostMapping("/users/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, 
                             BindingResult result, 
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/user-edit";
        }
        
        userService.save(user);
        redirectAttributes.addFlashAttribute("success", "User updated successfully");
        return "redirect:/admin/users";
    }
    
    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "User deleted successfully");
        return "redirect:/admin/users";
    }
    
    // ===== ANIMAL MANAGEMENT =====
    
    @GetMapping("/animals")
    public String manageAnimals(Model model) {
        model.addAttribute("animals", animalService.findAll());
        return "admin/animals";
    }
    
    @GetMapping("/animals/add")
    public String addAnimalForm(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("novels", novelService.findAll());
        return "admin/animal-form";
    }
    
    @GetMapping("/animals/edit/{id}")
    public String editAnimalForm(@PathVariable Long id, Model model) {
        Animal animal = animalService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid animal Id:" + id));
        model.addAttribute("animal", animal);
        model.addAttribute("novels", novelService.findAll());
        return "admin/animal-form";
    }
    
    @PostMapping("/animals/save")
    public String saveAnimal(@Valid @ModelAttribute("animal") Animal animal, 
                              BindingResult result, 
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("novels", novelService.findAll());
            return "admin/animal-form";
        }
        
        animalService.save(animal);
        redirectAttributes.addFlashAttribute("success", "Animal saved successfully");
        return "redirect:/admin/animals";
    }
    
    @PostMapping("/animals/delete/{id}")
    public String deleteAnimal(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        animalService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Animal deleted successfully");
        return "redirect:/admin/animals";
    }
    
    // ===== NOVEL MANAGEMENT =====
    
    @GetMapping("/novels")
    public String manageNovels(Model model) {
        model.addAttribute("novels", novelService.findAll());
        return "admin/novels";
    }
    
    @GetMapping("/novels/add")
    public String addNovelForm(Model model) {
        model.addAttribute("novel", new Novel());
        return "admin/novel-form";
    }
    
    @GetMapping("/novels/edit/{id}")
    public String editNovelForm(@PathVariable Long id, Model model) {
        Novel novel = novelService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid novel Id:" + id));
        model.addAttribute("novel", novel);
        return "admin/novel-form";
    }
    
    @PostMapping("/novels/save")
    public String saveNovel(@Valid @ModelAttribute("novel") Novel novel, 
                             BindingResult result, 
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/novel-form";
        }
        
        novelService.save(novel);
        redirectAttributes.addFlashAttribute("success", "Novel saved successfully");
        return "redirect:/admin/novels";
    }
    
    @PostMapping("/novels/delete/{id}")
    public String deleteNovel(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        novelService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Novel deleted successfully");
        return "redirect:/admin/novels";
    }
    
    // ===== MESSAGE MANAGEMENT =====
    
    @GetMapping("/messages")
    public String viewMessages(Model model) {
        model.addAttribute("messages", messageService.findAllByOrderBySentAtDesc());
        return "admin/messages";
    }
    
    @GetMapping("/messages/view/{id}")
    public String viewMessage(@PathVariable Long id, Model model) {
        Message message = messageService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid message Id:" + id));
        
        // Mark message as read if it's not already
        if (!message.isRead()) {
            message.setRead(true);
            messageService.save(message);
        }
        
        model.addAttribute("message", message);
        return "admin/message-view";
    }
    
    @PostMapping("/messages/delete/{id}")
    public String deleteMessage(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        messageService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Message deleted successfully");
        return "redirect:/admin/messages";
    }
}
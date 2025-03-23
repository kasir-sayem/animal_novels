package com.animalnovels.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.animalnovels.model.Animal;
import com.animalnovels.model.Message;
import com.animalnovels.model.Novel;
import com.animalnovels.model.User;
import com.animalnovels.repository.MessageRepository;
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


    @Autowired
    private MessageRepository messageRepository;
    
    // Main Dashboard
    @GetMapping
    public String adminDashboard() {
        return "admin";
    }

    @GetMapping("/test")
    public String testPage() {
        return "admin/test";
    }

    @GetMapping("/animals-simple")
    public String simpleAnimalsPage() {
        return "admin/animals-simple";
    }
    
    
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

    @PostMapping("/animals/save")
    public String saveAnimal(@Valid @ModelAttribute("animal") Animal animal, 
                         BindingResult result,
                         @RequestParam(value = "novelIds", required = false) List<Long> novelIds,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("novels", novelService.findAll());
            return "admin/animal-form";
        }
                // Assign ID for new animals
        if (animal.getId() == null) {
            // Get the maximum ID from all existing animals
            Long maxId = animalService.findAll().stream()
                    .map(Animal::getId)
                    .max(Long::compareTo)
                    .orElse(0L);
            animal.setId(maxId + 1);
        }
        // Handle the many-to-many relationship with novels
        if (novelIds != null && !novelIds.isEmpty()) {
            Set<Novel> selectedNovels = new HashSet<>();
            for (Long novelId : novelIds) {
                novelService.findById(novelId).ifPresent(selectedNovels::add);
            }
            animal.setNovels(selectedNovels);
        } else {
            animal.setNovels(new HashSet<>());
        }
        
        animalService.save(animal);
        redirectAttributes.addFlashAttribute("success", "Animal saved successfully");
        return "redirect:/admin/animals";
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
        
        // Assign ID for new novels
        if (novel.getId() == null) {
            // Get the maximum ID from all existing novels
            Long maxId = novelService.findAll().stream()
                    .map(Novel::getId)
                    .max(Long::compareTo)
                    .orElse(0L);
            novel.setId(maxId + 1);
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
    @GetMapping("/test-message")
    public String createTestMessage(RedirectAttributes redirectAttributes) {
        try {
            Message testMessage = new Message();
            testMessage.setName("Test User");
            testMessage.setEmail("test@example.com");
            testMessage.setSubject("Test Message");
            testMessage.setContent("This is a test message created for debugging.");
            
            Message savedMessage = messageService.save(testMessage);
            System.out.println("Created test message with ID: " + savedMessage.getId());
            
            redirectAttributes.addFlashAttribute("success", "Test message created!");
        } catch (Exception e) {
            System.err.println("Error creating test message: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to create test message");
        }
        
        return "redirect:/admin/messages";
    }
    
    // @GetMapping("/messages")
    // public String viewMessages(Model model) {
    //     model.addAttribute("messages", messageService.findAllByOrderBySentAtDesc());
    //     return "admin/messages";
    // }
        @GetMapping("/messages")
public String viewMessages(Model model) {
    try {
        // First get a count directly from repository to verify data exists
        long messageCount = messageRepository.count();
        System.out.println("Total messages in database: " + messageCount);
        
        // Get all messages without any sorting to verify basic retrieval works
        List<Message> allMessages = messageRepository.findAll();
        System.out.println("Retrieved messages (unsorted): " + allMessages.size());
        
        // Now try the sorted version
        List<Message> sortedMessages = messageService.findAllByOrderBySentAtDesc();
        System.out.println("Retrieved messages (sorted): " + sortedMessages.size());
        
        // Add to model
        model.addAttribute("messages", sortedMessages);
        
        // Debug first message if any exist
        if (!sortedMessages.isEmpty()) {
            Message first = sortedMessages.get(0);
            System.out.println("First message - ID: " + first.getId() + 
                               ", Subject: " + first.getSubject() +
                               ", From: " + first.getName());
        }
        
        return "admin/messages";
    } catch (Exception e) {
        // Print any errors
        System.err.println("Error retrieving messages: " + e.getMessage());
        e.printStackTrace();
        
        // Return empty list if there's an error
        model.addAttribute("messages", new ArrayList<>());
        return "admin/messages";
    }
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
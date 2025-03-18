package com.animalnovels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.animalnovels.model.Message;
import com.animalnovels.service.MessageService;

import jakarta.validation.Valid;

@Controller
public class ContactController {
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("message", new Message());
        return "contact";
    }
    
    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute("message") Message message,
                               BindingResult result, Authentication authentication) {
        
        if (result.hasErrors()) {
            return "contact";
        }
        
        if (authentication != null && authentication.isAuthenticated()) {
            messageService.saveWithUser(message, authentication.getName());
        } else {
            messageService.save(message);
        }
        
        return "redirect:/contact?success";
    }
    
    @GetMapping("/messages")
    public String viewMessages(Model model) {
        model.addAttribute("messages", messageService.findAllByOrderBySentAtDesc());
        return "messages";
    }
}

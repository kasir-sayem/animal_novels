package com.animalnovels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping
    public String adminDashboard() {
        return "admin";
    }
    
    @GetMapping("/api-docs")
    public String apiDocs() {
        return "api-docs";
    }
}

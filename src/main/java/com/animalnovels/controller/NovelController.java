package com.animalnovels.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.animalnovels.model.Novel;
import com.animalnovels.service.NovelService;

@Controller
@RequestMapping("/novels")
public class NovelController {
    
    @Autowired
    private NovelService novelService;
    
    @GetMapping
    public String listNovels(Model model) {
        List<Novel> novels = novelService.findAll();
        model.addAttribute("novels", novels);
        return "novels/list";
    }
    
    @GetMapping("/{id}")
    public String viewNovel(@PathVariable Long id, Model model) {
        Optional<Novel> novel = novelService.findById(id);
        
        if (novel.isPresent()) {
            model.addAttribute("novel", novel.get());
            return "novels/view";
        } else {
            return "redirect:/novels";
        }
    }
}

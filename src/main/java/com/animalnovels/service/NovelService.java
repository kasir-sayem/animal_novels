package com.animalnovels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animalnovels.model.Novel;
import com.animalnovels.repository.NovelRepository;

@Service
public class NovelService {
    
    @Autowired
    private NovelRepository novelRepository;
    
    public List<Novel> findAll() {
        return novelRepository.findAll();
    }
    
    public Optional<Novel> findById(Long id) {
        return novelRepository.findById(id);
    }
    
    public List<Novel> findByYearRange(Integer startYear, Integer endYear) {
        return novelRepository.findByPyearBetween(startYear, endYear);
    }
    
    public List<Novel> findByTitleContaining(String title) {
        return novelRepository.findByTitleContaining(title);
    }
    
    public Novel save(Novel novel) {
        return novelRepository.save(novel);
    }
    
    public void deleteById(Long id) {
        novelRepository.deleteById(id);
    }
}

package com.animalnovels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animalnovels.model.Novel;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Long> {
    List<Novel> findByPyearBetween(Integer startYear, Integer endYear);
    List<Novel> findByTitleContaining(String title);
}

package com.animalnovels.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "animals")
public class Animal {
    
    @Id
    private Long id;
    
    @Column(nullable = false)
    private String aname;
    
    @Column(nullable = false)
    private String species;
    
    @ManyToMany
    @JoinTable(
        name = "connecting",
        joinColumns = @JoinColumn(name = "animalid"),
        inverseJoinColumns = @JoinColumn(name = "novelid")
    )
    private Set<Novel> novels = new HashSet<>();
    
    // Constructors
    public Animal() {}
    
    public Animal(Long id, String aname, String species) {
        this.id = id;
        this.aname = aname;
        this.species = species;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAname() {
        return aname;
    }
    
    public void setAname(String aname) {
        this.aname = aname;
    }
    
    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public Set<Novel> getNovels() {
        return novels;
    }
    
    public void setNovels(Set<Novel> novels) {
        this.novels = novels;
    }
}


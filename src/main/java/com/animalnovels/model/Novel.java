package com.animalnovels.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "novels")
public class Novel {
    
    @Id
    private Long id;
    
    @Column(nullable = false)
    private Integer pyear;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String publisher;
    
    @ManyToMany(mappedBy = "novels")
    private Set<Animal> animals = new HashSet<>();
    
    // Constructors
    public Novel() {}
    
    public Novel(Long id, Integer pyear, String title, String publisher) {
        this.id = id;
        this.pyear = pyear;
        this.title = title;
        this.publisher = publisher;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getPyear() {
        return pyear;
    }
    
    public void setPyear(Integer pyear) {
        this.pyear = pyear;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public Set<Animal> getAnimals() {
        return animals;
    }
    
    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
}
package com.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="c_id")
    private UUID id;
    private String name;
    private Date roastDate;
    private String brand;
    private String secondaryFlavours;
    private String location;
    private String specie;
    private String method;
    private String color; //TODO: implementar restrição de domínio
    private LocalDateTime modificationDateTime = LocalDateTime.now();

    @ManyToMany(mappedBy = "coffeeUsed")
    @JsonIgnore
    private Set<Recipe> recipes = new HashSet<Recipe>();
    private UUID userId;

    public Coffee(){
        id = UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getRoastDate() {
        return roastDate;
    }
    public void setRoastDate(Date roastDate) {
        this.roastDate = roastDate;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getSecondaryFlavours() {
        return secondaryFlavours;
    }
    public void setSecondaryFlavours(String secondaryFlavours) {
        this.secondaryFlavours = secondaryFlavours;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getSpecie() {
        return specie;
    }
    public void setSpecie(String specie) {
        this.specie = specie;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public LocalDateTime getModificationDateTime() {
        return modificationDateTime;
    }
    public void setModificationDateTime(LocalDateTime modificationDateTime) {
        this.modificationDateTime = modificationDateTime;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public Set<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}

package com.application.entities;

import jakarta.persistence.*;
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

    //TODO:add creationDate

    @ManyToMany(mappedBy = "coffeeUsed")
    private Set<Recipe> recipes = new HashSet<Recipe>();

    @ManyToOne
    @JoinColumn(name="u_id", nullable=false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.getCoffee().add(this);
    }
}

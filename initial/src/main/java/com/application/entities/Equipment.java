package com.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="e_id")
    private UUID id;
    private String brand;
    private String model;
    private EquipmentType type;

    @ManyToMany(mappedBy = "equipmentUsed")
    @JsonIgnore
    private Set<Recipe> recipes = new HashSet<Recipe>();

    @ManyToMany(mappedBy = "equipments")
    @JsonIgnore
    private Set<User> users = new HashSet<User>();

    public Equipment() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

}

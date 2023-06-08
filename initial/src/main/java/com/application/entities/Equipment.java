package com.application.entities;

import com.application.enums.EquipmentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="e_id")
    private UUID id;
    @Transient
    private UUID internalId;
    @NotBlank(message = "Brand is mandatory")
    private String brand;
    @NotBlank(message = "Model is mandatory")
    private String model;
    private EquipmentType type;
    @ManyToMany(mappedBy = "equipmentUsed")
    @JsonIgnore
    private Set<Recipe> recipes = new HashSet<>();
    @ManyToMany(mappedBy = "equipments")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Equipment() { id = UUID.randomUUID(); }

    public UUID getId() { return id; }
    public UUID getInternalId() { return internalId; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public EquipmentType getType() { return type; }
    public void setType(EquipmentType type) { this.type = type; }
}
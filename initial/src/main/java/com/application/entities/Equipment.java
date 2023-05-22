package com.application.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String brand;
    private String model;

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
}

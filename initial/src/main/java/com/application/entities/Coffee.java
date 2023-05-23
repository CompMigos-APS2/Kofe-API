package com.application.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


import java.util.Date;
import java.util.UUID;


@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String name;
    private Date roastDate;
    private String brand;
    private String secondaryFlavours;
    private String location;
    private String specie;
    private String method;
    private String color; //TODO: implementar restrição de domínio

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


}

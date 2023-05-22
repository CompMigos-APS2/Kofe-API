package com.application.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String extractionMethod;
    private int coffeeQty;
    private int waterQty;
    private Time time;
    private String preparationMethod;
    private Date date;
    private float rating;
    private List<String> commentsList;

    public Recipe() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getExtractionMethod() {
        return extractionMethod;
    }

    public void setExtractionMethod(String extractionMethod) {
        this.extractionMethod = extractionMethod;
    }

    public int getCoffeeQty() {
        return coffeeQty;
    }

    public void setCoffeeQty(int coffeeQty) {
        this.coffeeQty = coffeeQty;
    }

    public int getWaterQty() {
        return waterQty;
    }

    public void setWaterQty(int waterQty) {
        this.waterQty = waterQty;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<String> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<String> commentsList) {
        this.commentsList = commentsList;
    }
}

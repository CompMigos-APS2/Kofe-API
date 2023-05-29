package com.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

@Entity

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="r_id")
    private UUID id;
    private String title;
    private String extractionMethod;
    private int coffeeQty;
    private int waterQty;
    private Time time;
    private String preparationMethod;
    private Date date;
    private float rating;
    private LocalDateTime modificationDateTime = LocalDateTime.now();
    private List<String> commentsList;
    private List<String> coffeeStringIds = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_coffee",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id")
    )
    private Set<Coffee> coffeeUsed = new HashSet<>();
    private List<String> equipmentStringIds = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_equipment",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipmentUsed = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="u_id", nullable=false)
    private User user;
    private String userId;

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
    public List<String> getCoffeeStringIds() {
        return coffeeStringIds;
    }
    public void setCoffeeStringIds(List<String> coffeeStringIds) {
        this.coffeeStringIds = coffeeStringIds;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Set<Coffee> getCoffeeUsed() {
        return coffeeUsed;
    }
    public void setCoffeeUsed(Set<Coffee> coffeeUsed) {
        this.coffeeUsed = coffeeUsed;
    }
    public List<String> getEquipmentStringIds() {
        return equipmentStringIds;
    }
    public void setEquipmentStringIds(List<String> equipmentStringIds) {
        this.equipmentStringIds = equipmentStringIds;
    }
    public Set<Equipment> getEquipmentUsed() {
        return equipmentUsed;
    }
    public void setEquipmentUsed(Set<Equipment> equipmentUsed) {
        this.equipmentUsed = equipmentUsed;
    }
    public void addCoffeeUsed(Coffee coffeeToAdd) {
        coffeeUsed.add(coffeeToAdd);
    }
    public boolean removeCoffeeUsed(Coffee coffeeToRemove) {
        return coffeeUsed.remove(coffeeToRemove);
    }
    public void addEquipmentUsed(Equipment equipmentToAdd) {
        equipmentUsed.add(equipmentToAdd);
    }
    public boolean removeEquipmentUsed(Equipment equipmentToRemove) {
        return equipmentUsed.remove(equipmentToRemove);
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDateTime getModificationDateTime() {
        return modificationDateTime;
    }
    public void setModificationDateTime(LocalDateTime modificationDateTime) {
        this.modificationDateTime = modificationDateTime;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}

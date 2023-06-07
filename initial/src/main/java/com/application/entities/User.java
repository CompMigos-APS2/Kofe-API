package com.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="u_id")
    private UUID id;
    @JsonIgnore
    private String authId;
    @JsonIgnore
    @Transient
    private boolean updateFlag = false;
    private String name;
    private String email;
    private String login;
    private String password;
    private Date birthDate;
    private String address;
    private List<UUID> equipmentIds = new ArrayList<>();
    private List<UUID> coffeesIds = new ArrayList<>();
    private List<UUID> recipesIds = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "user_equipment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();

    public User(){
    id = UUID.randomUUID();
    }

    public boolean hasToUpdate(){ return updateFlag; }
    public UUID getId() {
        return id;
    }
    public String getAuthId() { return authId; }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<UUID> getEquipmentIds() {
        return equipmentIds;
    }
    public void setEquipmentIds(List<UUID> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }
    public Set<Equipment> getEquipments() {
        return equipments;
    }
    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }
    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }
    public void removeEquipment(Equipment equipment) {
        equipments.remove(equipment);
    }

    public List<UUID> getCoffeesIds() {
        return coffeesIds;
    }
    public void setCoffeesIds(List<UUID> coffeesIds) {
        this.coffeesIds = coffeesIds;
    }
    public void updateCoffeesIds(UUID id){
        for(UUID coffee : coffeesIds)
            if(coffee.equals(id))
                return;
        coffeesIds.add(id);
    }
    public List<UUID> getRecipesIds() {
        return recipesIds;
    }
    public void setRecipesIds(List<UUID> recipesIds) {
        this.recipesIds = recipesIds;
    }
    public void updateRecipesIds(UUID id){
        for(UUID recipe : recipesIds)
            if(recipe.equals(id))
                return;
        recipesIds.add(id);
    }
}

package com.application.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="u_id")
    private UUID id;
    private String name;
    private String email;
    private String login;
    private String password;
    private Date birthDate;
    private String address;
    private List<String> equipmentStringIds = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private Set<Coffee> coffees = new HashSet<>();

    @OneToMany(mappedBy="user")
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_equipment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();

    public User(){
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
    public Set<Coffee> getCoffees() {
        return coffees;
    }
    public void setCoffees(Set<Coffee> coffees) {
        this.coffees = coffees;
    }
    public Set<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
    public List<String> getEquipmentStringIds() {
        return equipmentStringIds;
    }
    public void setEquipmentStringIds(List<String> equipmentStringIds) {
        this.equipmentStringIds = equipmentStringIds;
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
}

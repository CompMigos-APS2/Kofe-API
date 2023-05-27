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
    private String name;
    private String email;
    private String login;
    private String password;
    private Date birthDate;
    private String address;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private Set<Coffee> coffee = new HashSet<>();

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private Set<Recipe> recipe = new HashSet<>();

//    private List<Equipment> equipmentList;
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

    public Set<Coffee> getCoffee() {
        return coffee;
    }

    public void setCoffee(Set<Coffee> coffee) {
        this.coffee = coffee;
    }

    public Set<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(Set<Recipe> recipe) {
        this.recipe = recipe;
    }
}

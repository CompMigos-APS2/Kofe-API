package com.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Stats {
    public Stats(User user){
        this.user = user;
    }
    @JsonIgnore
    private User user;
    private int coffeeQty;
    private int recipeQty;
    private int equipmentQty;

    public int getCoffeeQty(){ return coffeeQty; }
    public int getRecipeQtyQty(){ return recipeQty; }
    public int getEquipmentQty(){ return equipmentQty; }
    @JsonIgnore
    public boolean hasToUpdate(){
        return user.hasToUpdate();
    }

    public void calculate(){
        var coffees = user.getCoffeesIds();
        var recipes = user.getRecipesIds();
        var equipments = user.getEquipmentIds();

        coffeeQty = coffees.size();
        recipeQty = recipes.size();
        equipmentQty = equipments.size();
    }
}

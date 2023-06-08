package com.application.entities;

public class Stats {
    private int coffeeQty;
    private int recipeQty;
    private int equipmentQty;
    private boolean updateFlag = false;

    public int getCoffeeQty(){ return coffeeQty; }
    public void setCoffeeQty(int qty) { this.coffeeQty = qty; }
    public int getRecipeQty(){ return recipeQty; }
    public void setRecipeQty(int qty) { this.recipeQty = qty; }
    public int getEquipmentQty(){ return equipmentQty; }
    public void setEquipmentQtyQty(int qty) { this.equipmentQty = qty; }
    public void setUpdateFlag(boolean flag){ updateFlag = flag; }
    public boolean hasToUpdate(){ return updateFlag; }

    public void calculate(User newUserData){
        var coffees = newUserData.getCoffeesIds();
        var recipes = newUserData.getRecipesIds();
        var equipments = newUserData.getEquipmentIds();

        coffeeQty = coffees.size();
        recipeQty = recipes.size();
        equipmentQty = equipments.size();
    }
}
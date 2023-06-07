package com.application.entities;

public class Stats {
    public Stats(User user){
        this.user = user;
    }
    private User user;
    public boolean hasToUpdate(){
        return user.hasToUpdate();
    }

    public void calculate(){

    }
}

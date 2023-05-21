package com.application.handlers;

import com.application.base.BaseState;
import com.application.entities.Recipe;
import com.application.states.RecipeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Enumeration;

@RestController
@RequestMapping("/recipe")
public class RecipeHandler {
    private BaseState<Recipe> state;
    @Autowired
    public RecipeHandler(RecipeState state){
        this.state = state;
    }
    @RequestMapping("/")
    public Collection<Recipe> GetAll(){
        System.out.println("oi");
        return state.GetAll();
    }
    @RequestMapping("/get")
    public String Get(){
        return "teste";
    }

    @PostMapping("/create")
    public void Create(@RequestBody Recipe obj){
        System.out.println(obj);
        state.TryInsert(obj);
    }
}

package com.application.handlers;

import com.application.base.BaseState;
import com.application.entities.Coffee;
import com.application.states.CoffeeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffee")
public class CoffeeHandler {
    private BaseState<Coffee> state;
    @Autowired
    public CoffeeHandler(CoffeeState state){
        this.state = state;
    }
    @RequestMapping("/get")
    public String Get(){
        return "teste";
    }

    @PostMapping("/create")
    public void Create(@RequestBody Coffee obj){
        state.TryInsert(obj);
    }
}

package com.application.handlers;

//import com.application.base.BaseState;
import com.application.entities.Coffee;
import com.application.repository.CoffeeRepository;
//import com.application.states.CoffeeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/coffee")
public class CoffeeHandler {
    private final CoffeeRepository repository;
    @Autowired
    public CoffeeHandler(CoffeeRepository repository){
        this.repository = repository;
    }
    @RequestMapping("/get")
    public List<Coffee> get(){
        return repository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Coffee obj){
        repository.save(obj);
    }

    @RequestMapping("/getById")
    public Optional<Coffee> getById(String id){
        UUID formattedId = UUID.fromString(id);
        return repository.findById(formattedId);
    }
    @RequestMapping("/getByName")
    public List<Coffee> getByName(String name){
        return repository.findByName(name);
    }

    @RequestMapping("/deleteById")
    public void deleteById(String id){
        UUID formattedId = UUID.fromString(id);
        repository.deleteById(formattedId);
    }

}

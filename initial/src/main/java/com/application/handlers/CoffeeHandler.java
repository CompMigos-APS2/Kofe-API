package com.application.handlers;

import com.application.entities.Coffee;
import com.application.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeHandler extends GenericHandler<Coffee, CoffeeRepository> {
    @Autowired
    public CoffeeHandler(CoffeeRepository repository) {
        super(repository);
    }
    @RequestMapping("/getByName")
    public List<Coffee> getByName(String name){
        return repository.findByName(name);
    }

}

package com.application.handlers;

import com.application.entities.Coffee;
import com.application.entities.User;
import com.application.repository.CoffeeRepository;
import com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/coffee")
public class CoffeeHandler extends GenericHandler<Coffee, CoffeeRepository> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public CoffeeHandler(CoffeeRepository repository) {
        super(repository);
    }
    @RequestMapping("/getByName")
    public ResponseEntity<List<Coffee>> getByName(String name){
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping("/deleteCoffeeById")
    public ResponseEntity<List<Object>> deleteCoffeeById(String id) {

        UUID formattedId = UUID.fromString(id);
        List<Object> recipeList = repository.findRecipesWithCoffee(formattedId);
        if(recipeList.size() == 0) {
            repository.deleteById(formattedId);
            return new ResponseEntity<>(recipeList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recipeList, HttpStatus.CONFLICT);
    }

    @PostMapping("/save")
    public ResponseEntity<Coffee> save(@RequestBody Coffee obj) {
        UUID userId = obj.getUserId();
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            //retornar algm exception de not found aqui
        }
        obj.setUserId(userId);
        Coffee savedCoffee = repository.save(obj);

        UUID coffeeId = savedCoffee.getId();
        user.get().updateCoffeesIds(coffeeId);

        userRepository.save(user.get());

        return new ResponseEntity<>(savedCoffee, HttpStatus.CREATED);
    }

}

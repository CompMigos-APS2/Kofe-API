package com.application.handlers;

import com.application.entities.Coffee;
import com.application.entities.Equipment;
import com.application.entities.Recipe;
import com.application.entities.User;
import com.application.repository.CoffeeRepository;

import com.application.repository.EquipmentRepository;
import com.application.repository.RecipeRepository;
import com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
@RequestMapping("/recipe")

public class RecipeHandler extends GenericHandler<Recipe, RecipeRepository> {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public RecipeHandler(RecipeRepository repository) {
        super(repository);
    }
    @RequestMapping("/getByTitle")
    public ResponseEntity<List<Recipe>> getByTitle(String title){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
        
        return new ResponseEntity<>(repository.findByTitle(title), headers, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Recipe> save(@RequestBody Recipe obj) {
        UUID userStringId = obj.getUserId();
        Optional<User> user = userRepository.findById(userStringId);
        if(user.isEmpty()){
            //retornar algm exception de not found aqui
        }
        obj.setUserId(userStringId);

        List<UUID> coffeeIds = obj.getCoffeeIds();
        for(UUID coffeeId : coffeeIds) {
            Optional<Coffee> coffee = coffeeRepository.findById(coffeeId);
            if(coffee.isEmpty())
                continue;
            obj.addCoffeeUsed(coffee.get());
        }
        List<UUID> equipmentIds = obj.getEquipmentIds();
        for(UUID equipmentId : equipmentIds) {
            Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);
            if(equipment.isEmpty())
                continue;
            obj.addEquipmentUsed(equipment.get());
        }
        Recipe savedRecipe = repository.save(obj);

        UUID recipeId = savedRecipe.getId();
        user.get().updateRecipesIds(recipeId);
        userRepository.save(user.get());
      
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<>(savedRecipe, headers, HttpStatus.CREATED);
    }
}

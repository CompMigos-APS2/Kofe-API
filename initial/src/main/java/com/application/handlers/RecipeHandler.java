package com.application.handlers;

import com.application.entities.Coffee;
import com.application.entities.Equipment;
import com.application.entities.Recipe;
import com.application.repository.CoffeeRepository;

import com.application.repository.EquipmentRepository;
import com.application.repository.RecipeRepository;
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
@RequestMapping("/recipe")

public class RecipeHandler extends GenericHandler<Recipe, RecipeRepository> {
    @Autowired
    public RecipeHandler(RecipeRepository repository) {
        super(repository);
    }
    @RequestMapping("/getByTitle")
    public ResponseEntity<List<Recipe>> getByTitle(String title){
        return new ResponseEntity<>(repository.findByTitle(title), HttpStatus.OK);
    }

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @PostMapping("/save")
    public ResponseEntity<Recipe> save(@RequestBody Recipe obj) {
        List<String> coffeeStringIds = obj.getCoffeeStringIds();
        for(String coffeeStringId : coffeeStringIds) {
            Optional<Coffee> coffee = coffeeRepository.findById(UUID.fromString(coffeeStringId));
            if(coffee.isEmpty())
                continue;
            obj.addCoffeeUsed(coffee.get());
        }
        List<String> equipmentStringIds = obj.getEquipmentStringIds();
        for(String equipmentStringId : equipmentStringIds) {
            Optional<Equipment> equipment = equipmentRepository.findById(UUID.fromString(equipmentStringId));
            if(equipment.isEmpty())
                continue;
            obj.addEquipmentUsed(equipment.get());
        }
        Recipe savedRecipe = repository.save(obj);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

}

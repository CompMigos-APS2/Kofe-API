package com.application.handlers;

import com.application.entities.Coffee;
import com.application.entities.Equipment;
import com.application.entities.Recipe;
import com.application.entities.User;
import com.application.filters.RecipeFilter;
import com.application.repository.CoffeeRepository;

import com.application.repository.EquipmentRepository;
import com.application.repository.RecipeRepository;
import com.application.repository.UserRepository;
import jakarta.persistence.EntityManager;
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
    private CoffeeRepository coffeeRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public RecipeHandler(RecipeRepository repository, RecipeFilter filter, EntityManager em) {
        super(repository);
        this.filter = new RecipeFilter(em);
    }
    @PostMapping("/save")
    public ResponseEntity<Recipe> save(@RequestBody Recipe obj) {
        UUID userStringId = obj.getUserId();
        Optional<User> user = userRepository.findById(userStringId);
        if(user.isEmpty()){
            //retornar algm exception de not found aqui
        }
        obj.setUserId(userStringId);

        obj.getCoffeeIds().forEach(coffeeId -> coffeeRepository.findById(coffeeId)
                .ifPresent(obj::addCoffeeUsed));

        obj.getEquipmentIds().forEach(equipmentId -> equipmentRepository.findById(equipmentId)
                .ifPresent(obj::addEquipmentUsed));

        Recipe savedRecipe = repository.save(obj);

        UUID recipeId = savedRecipe.getId();
        user.get().updateRecipesIds(recipeId);
        userRepository.save(user.get());

        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }
}

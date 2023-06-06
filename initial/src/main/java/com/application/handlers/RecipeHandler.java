package com.application.handlers;

import com.application.entities.Recipe;
import com.application.entities.User;

import com.application.exceptions.NotFoundException;
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
        UUID userId = obj.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        obj.setUserId(userId);

        obj.getCoffeeIds().forEach(coffeeId -> coffeeRepository.findById(coffeeId)
                .ifPresent(obj::addCoffeeUsed));
        obj.getEquipmentIds().forEach(equipmentId -> equipmentRepository.findById(equipmentId)
                .ifPresent(obj::addEquipmentUsed));

        Recipe savedRecipe = repository.save(obj);

        user.updateRecipesIds(savedRecipe.getId());
        userRepository.save(user);

        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

}

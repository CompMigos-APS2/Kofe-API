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
import org.springframework.validation.annotation.Validated;

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
    private StatsHandler statsHandler;

    @Autowired
    public RecipeHandler(RecipeRepository repository, RecipeFilter filter, EntityManager em) {
        super(repository);
        this.filter = new RecipeFilter(em);
    }
    @Validated
    @PostMapping("/save")
    public ResponseEntity<Recipe> save(@RequestBody Recipe obj) {
        UUID userId = obj.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));


        obj.getCoffeeIds().forEach(coffeeId -> coffeeRepository.findById(coffeeId)
                .ifPresentOrElse(
                    obj::addCoffeeUsed,
                    () -> { throw new NotFoundException("Coffee not found"); }
                ));
        obj.getEquipmentIds().forEach(equipmentId -> equipmentRepository.findById(equipmentId)
                .ifPresentOrElse(
                    obj::addEquipmentUsed,
                    () -> { throw new NotFoundException("Equipment not found"); }
                ));

        Recipe savedRecipe = repository.save(obj);

        user.updateRecipesIds(savedRecipe.getId());

        statsHandler.setUserUpdated(userId);
        userRepository.save(user);

        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

}

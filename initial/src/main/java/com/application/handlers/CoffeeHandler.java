package com.application.handlers;

import com.application.entities.Coffee;
import com.application.entities.User;
import com.application.exceptions.NotFoundException;
import com.application.filters.CoffeeFilter;
import com.application.repository.CoffeeRepository;
import com.application.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coffee")
public class CoffeeHandler extends GenericHandler<Coffee, CoffeeRepository> {
    @Autowired
    private UserRepository userRepository;
    private final StatsHandler statsHandler;

    @Autowired
    public CoffeeHandler(CoffeeRepository repository, EntityManager em, StatsHandler sh) {
        super(repository);
        this.filter = new CoffeeFilter(em);
        this.statsHandler = sh;
    }

    @RequestMapping("/deleteCoffeeById")
    public ResponseEntity<List<Object>> deleteCoffeeById(String idString) {
        UUID id = UUID.fromString(idString);
        List<Object> recipeList = repository.findRecipesWithCoffee(id);

        if(recipeList.size() > 0) return new ResponseEntity<>(recipeList, HttpStatus.CONFLICT);

        repository.deleteById(id);
        return new ResponseEntity<>(recipeList, HttpStatus.NO_CONTENT);
    }

    @Validated
    @PostMapping("/save")
    public ResponseEntity<Coffee> save(@RequestBody Coffee obj) {
        UUID userId = obj.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Coffee savedCoffee = repository.save(obj);

        UUID coffeeId = savedCoffee.getId();
        user.updateCoffeesIds(coffeeId);

        statsHandler.setUserUpdated(user.getId());
        userRepository.save(user);

        return new ResponseEntity<>(savedCoffee, HttpStatus.CREATED);
    }
}

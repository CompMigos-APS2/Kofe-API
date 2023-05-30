package com.application.handlers;

import com.application.entities.Equipment;
import com.application.entities.User;
import com.application.entities.Coffee;
import com.application.entities.Recipe;
import com.application.repository.EquipmentRepository;
import com.application.repository.RecipeRepository;
import com.application.repository.UserRepository;
import com.application.repository.CoffeeRepository;
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
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserHandler extends GenericHandler<User, UserRepository> {
    @Autowired
    public UserHandler(UserRepository repository) {
        super(repository);
    }
    @Autowired EquipmentRepository equipmentRepository;
    @Autowired CoffeeRepository coffeeRepository;
    @Autowired RecipeRepository recipeRepository;

    @RequestMapping("/getByEmail")
    public ResponseEntity<List<User>> getByEmail(String email){
        return new ResponseEntity<>(repository.findByEmail(email), HttpStatus.OK);
    }

    @RequestMapping("/login")
    public ResponseEntity<User> login(String email, String password){
        return new ResponseEntity<>(repository.login(email, password), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User obj) {
        List<UUID> equipmentIds = obj.getEquipmentIds();
        for(UUID equipmentId : equipmentIds) {
            Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);
            if(equipment.isEmpty())
                continue;
            obj.addEquipment(equipment.get());
        }
        User savedUser = repository.save(obj);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    // acho q da pra fazer isso de forma mais geral
    @RequestMapping("/getCoffees")
    public ResponseEntity<List<Coffee>> getCoffees(String id){
        UUID formattedId = UUID.fromString(id);
        Optional<User> user = repository.findById(formattedId);
        if(user.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<UUID> coffeeIds = user.get().getCoffeesIds();
        List<Coffee> coffees = new ArrayList<>();
        coffeeRepository.findAllById(coffeeIds).forEach(coffees::add);
        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @RequestMapping("/getEquipments")
    public ResponseEntity<List<Equipment>> getEquipments(String id){
        UUID formattedId = UUID.fromString(id);
        Optional<User> user = repository.findById(formattedId);
        if(user.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<UUID> equipmentIds = user.get().getEquipmentIds();
        List<Equipment> equipments = new ArrayList<>();
        equipmentRepository.findAllById(equipmentIds).forEach(equipments::add);
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }

    @RequestMapping("/getRecipes")
    public ResponseEntity<List<Recipe>> getRecipes(String id) {
        UUID formattedId = UUID.fromString(id);
        Optional<User> user = repository.findById(formattedId);
        if(user.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<UUID> recipeIds = user.get().getRecipesIds();
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAllById(recipeIds).forEach(recipes::add);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

}

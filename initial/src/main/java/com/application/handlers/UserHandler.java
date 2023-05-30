package com.application.handlers;

import com.application.entities.Equipment;
import com.application.entities.User;
import com.application.entities.Coffee;
import com.application.repository.EquipmentRepository;
import com.application.repository.UserRepository;
import com.application.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
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

    @RequestMapping("/getByEmail")
    public ResponseEntity<List<User>> getByEmail(String email){
        return new ResponseEntity<>(repository.findByEmail(email), HttpStatus.OK);
    }

    @RequestMapping("/login")
    public ResponseEntity<User> login(String email, String password){
        return new ResponseEntity<>(repository.login(email, password), HttpStatus.OK);
    }

    @Autowired EquipmentRepository equipmentRepository;
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

    @Autowired 
    private CoffeeRepository coffeeRepository;
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
}

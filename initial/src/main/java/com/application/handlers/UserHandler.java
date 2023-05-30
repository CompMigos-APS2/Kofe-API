package com.application.handlers;

import com.application.entities.Equipment;
import com.application.entities.User;
import com.application.repository.EquipmentRepository;
import com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

@RestController
@RequestMapping("/user")
public class UserHandler extends GenericHandler<User, UserRepository> {
    @Autowired
    public UserHandler(UserRepository repository) {
        super(repository);
    }

    @RequestMapping("/getByEmail")
    public ResponseEntity<List<User>> getByEmail(String email){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<>(repository.findByEmail(email), HttpStatus.OK);
    }

    @RequestMapping("/login")
    public ResponseEntity<User> login(String email, String password){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<>(repository.login(email, password), HttpStatus.OK);
    }

    @Autowired EquipmentRepository equipmentRepository;
    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User obj) {
        List<String> equipmentStringIds = obj.getEquipmentStringIds();
        for(String equipmentStringId : equipmentStringIds) {
            Optional<Equipment> equipment = equipmentRepository.findById(UUID.fromString(equipmentStringId));
            if(equipment.isEmpty())
                continue;
            obj.addEquipment(equipment.get());
        }
        User savedUser = repository.save(obj);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}

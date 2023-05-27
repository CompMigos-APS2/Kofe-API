package com.application.handlers;

import com.application.entities.User;
import com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

//    @RequestMapping("/deleteCoffeeById")
//    public ResponseEntity<List<Object>> deleteCoffeeById(String id) {
//
//        UUID formattedId = UUID.fromString(id);
//        List<Object> recipeList = repository.findRecipesWithCoffee(formattedId);
//        if(recipeList.size() == 0) {
//            repository.deleteById(formattedId);
//            return new ResponseEntity<>(recipeList, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(recipeList, HttpStatus.CONFLICT);
//    }

}

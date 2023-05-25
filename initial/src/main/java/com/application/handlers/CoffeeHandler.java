package com.application.handlers;

import com.application.entities.Coffee;
import com.application.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/coffee")
public class CoffeeHandler extends GenericHandler<Coffee, CoffeeRepository> {
    @Autowired
    public CoffeeHandler(CoffeeRepository repository) {
        super(repository);
    }
    @RequestMapping("/getByName")
    public ResponseEntity<List<Coffee>> getByName(String name){
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

}

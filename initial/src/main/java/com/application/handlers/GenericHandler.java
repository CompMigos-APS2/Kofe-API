package com.application.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
public class GenericHandler<T, TRepository extends JpaRepository<T, UUID>>{
    public TRepository repository;
    @Autowired
    public GenericHandler(TRepository repository){
        this.repository = repository;
    }
    @RequestMapping("/get")
    public ResponseEntity<List<T>> get(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<T> save(@RequestBody T obj){
        return new ResponseEntity<>(repository.save(obj), HttpStatus.CREATED);
    }

    @RequestMapping("/getById")
    public Optional<T> getById(String id){
        UUID formattedId = UUID.fromString(id);
        return repository.findById(formattedId);
    }

    @RequestMapping("/deleteById")
    public void deleteById(String id){
        UUID formattedId = UUID.fromString(id);
        repository.deleteById(formattedId);
    }
}

package com.application.handlers;

import com.application.filters.Filter;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GenericHandler<T, TRepository extends JpaRepository<T, UUID>>{
    protected TRepository repository;
    @PersistenceContext
    protected EntityManager em;
    protected Filter filter;
    @Autowired
    public GenericHandler(TRepository repository){ this.repository = repository; }

    public void setFilter(Filter filter){ this.filter = filter; }
    @PostMapping("/get")
    public ResponseEntity<List<T>> get(@RequestBody String jsonReq) throws JsonProcessingException {
        return new ResponseEntity<>(em.createQuery(filter.buildQuery(jsonReq)).getResultList(), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<T> save(@RequestBody T obj){ return new ResponseEntity<>(repository.save(obj), HttpStatus.CREATED); }
    @PostMapping("/batchSave")
    public List<ResponseEntity<T>> batchSave(@RequestBody List<T> objList){
        List<ResponseEntity<T>> responses = new ArrayList<>();
        for(var obj : objList){
            responses.add(save(obj));
        }
        return responses;
    }
    @RequestMapping("/deleteById")
    public ResponseEntity<HttpStatus> deleteById(String id){
        UUID formattedId = UUID.fromString(id);
        repository.deleteById(formattedId);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.application.handlers;

import com.application.entities.Stats;
import com.application.entities.User;
import com.application.repository.CoffeeRepository;
import com.application.repository.RecipeRepository;
import com.application.repository.UserRepository;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/stats")

public class StatsHandler {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;
    private HashMap<UUID, Stats> monitoredAccounts = new HashMap<>();
    private boolean hasToUpdateUserList = false;
    public void setUserListUpdated(boolean flag){
        hasToUpdateUserList = flag;
    }
    public void setUserUpdated(UUID id){
        var stat = monitoredAccounts.get(id);
        stat.setUpdated(true);
    }

    @Autowired
    private StatsHandler(CoffeeRepository coffeeRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.coffeeRepository = coffeeRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;

        var activeUsers = this.userRepository.findAll();
        for(var user : activeUsers){
            var stats = new Stats();
            stats.calculate(user);
            monitoredAccounts.put(user.getId(), stats);
        }
    }
    @RequestMapping("/get")
    public ResponseEntity<Stats> get(String id){
        return new ResponseEntity<>(monitoredAccounts.get(UUID.fromString(id)), HttpStatus.ACCEPTED);
    }
    @Scheduled(fixedRate = 5000)
    private void calculate(){
        monitoredAccounts.forEach((key, value) -> {
            if(!value.hasToUpdate()) return;

            System.out.println("updating stat");
            value.calculate(userRepository.findById(key).get());
            value.setUpdated(false);
        });
    }

    @Scheduled(fixedRate = 1000)
    private void pollUsers(){
        if(!hasToUpdateUserList) return;

        var activeUsers = userRepository.findAll();
        for(var user : activeUsers){
            if(!monitoredAccounts.containsKey(user.getId())){
                System.out.println("adding user");
                var stats = new Stats();
                stats.calculate(user);
                monitoredAccounts.put(user.getId(), stats);
            }
        }
        hasToUpdateUserList = false;
    }
}

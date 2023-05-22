package com.application.states;

import com.application.base.BaseState;
import com.application.entities.Coffee;
import com.application.services.PersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoffeeState extends BaseState<Coffee> {
    @Autowired
    public CoffeeState(PersistenceManager persistenceManager){
        super(persistenceManager);
    }
}

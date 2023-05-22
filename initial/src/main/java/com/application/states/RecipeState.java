package com.application.states;

import com.application.base.BaseState;
import com.application.entities.Recipe;
import com.application.services.PersistenceManager;
import org.springframework.stereotype.Component;

@Component
public class RecipeState extends BaseState<Recipe> {
    public RecipeState(PersistenceManager persistenceManager){
        super(persistenceManager);
    }
}

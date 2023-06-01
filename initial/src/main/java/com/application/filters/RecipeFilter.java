package com.application.filters;

import com.application.entities.Equipment;
import com.application.entities.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Component;

@Component("RecipeFilter")
public class RecipeFilter implements Filter<Recipe> {

    @Override
    public CriteriaQuery<Recipe> buildQuery(String req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe effectiveJava = mapper.readValue(req, Recipe.class);
        return null;
    }
}

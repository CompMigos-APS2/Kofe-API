package com.application.filters;

import com.application.entities.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Component;

@Component("RecipeFilter")
public class RecipeFilter implements Filter<Recipe> {
    private EntityManager em;
    public RecipeFilter(EntityManager em){
        this.em = em;
    }

    @Override
    public CriteriaQuery<Recipe> buildQuery(String req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe filterObj = mapper.readValue(req, Recipe.class);

        var qb = em.getCriteriaBuilder();
        var query = qb.createQuery(Recipe.class);
        var root = query.from(Recipe.class);
        query.select(root);

        if(filterObj.getInternalId() != null) query.where(qb.equal(root.get("id"), filterObj.getInternalId()));
        if(filterObj.getExtractionMethod() != null) query.where(qb.equal(root.get("extractionMethod"), filterObj.getExtractionMethod()));
        if(filterObj.getTime() != null) query.where(qb.equal(root.get("time"), filterObj.getTime()));
        if(filterObj.getPreparationMethod() != null) query.where(qb.equal(root.get("preparationMethod"), filterObj.getPreparationMethod()));
        if(filterObj.getDate() != null) query.where(qb.equal(root.get("date"), filterObj.getDate()));
        if(filterObj.getTitle() != null) query.where(qb.equal(root.get("title"), filterObj.getTitle()));

        return query;
    }
}

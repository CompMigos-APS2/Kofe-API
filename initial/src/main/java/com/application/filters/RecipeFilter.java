package com.application.filters;

import com.application.entities.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import java.util.*;

@Component("RecipeFilter")
public class RecipeFilter implements Filter<Recipe> {
    private final EntityManager em;

    public RecipeFilter(EntityManager em){
        this.em = em;
    }

    @Override
    public CriteriaQuery<Recipe> buildQuery(String req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe filterObj = mapper.readValue(req, Recipe.class);
        List<Predicate> predicates = new ArrayList<>();

        var qb = em.getCriteriaBuilder();
        var query = qb.createQuery(Recipe.class);
        var root = query.from(Recipe.class);

        if(filterObj.getInternalId() != null) predicates.add(qb.equal(root.get("id"), filterObj.getInternalId()));
        if(filterObj.getExtractionMethod() != null) predicates.add(qb.equal(root.get("extractionMethod"), filterObj.getExtractionMethod()));
        if(filterObj.getTime() != null) predicates.add(qb.equal(root.get("time"), filterObj.getTime()));
        if(filterObj.getPreparationMethod() != null) predicates.add(qb.equal(root.get("preparationMethod"), filterObj.getPreparationMethod()));
        if(filterObj.getDate() != null) predicates.add(qb.equal(root.get("date"), filterObj.getDate()));
        if(filterObj.getTitle() != null) predicates.add(qb.equal(root.get("title"), filterObj.getTitle()));

        query.select(root).where(qb.and(predicates.toArray(new Predicate[0])));

        return query;
    }
}
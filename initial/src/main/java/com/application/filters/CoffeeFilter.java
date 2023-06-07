package com.application.filters;

import com.application.entities.Coffee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeFilter implements Filter<Coffee> {
    private EntityManager em;

    public CoffeeFilter(EntityManager em){
        this.em = em;
    }
    @Override
    public CriteriaQuery<Coffee> buildQuery(String req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Coffee filterObj = mapper.readValue(req, Coffee.class);

        var qb = em.getCriteriaBuilder();
        var query = qb.createQuery(Coffee.class);
        var root = query.from(Coffee.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filterObj.getName() != null) predicates.add(qb.equal(root.get("name"), filterObj.getName()));
        if(filterObj.getInternalId() != null) predicates.add(qb.equal(root.get("id"), filterObj.getInternalId()));
        if(filterObj.getRoastDate() != null) predicates.add(qb.equal(root.get("roastDate"), filterObj.getRoastDate()));
        if(filterObj.getBrand() != null) predicates.add(qb.equal(root.get("brand"), filterObj.getBrand()));
        if(filterObj.getSecondaryFlavours() != null) predicates.add(qb.equal(root.get("secondaryFlavours"), filterObj.getSecondaryFlavours()));
        if(filterObj.getLocation() != null) predicates.add(qb.equal(root.get("location"), filterObj.getLocation()));
        if(filterObj.getSpecie() != null) predicates.add(qb.equal(root.get("specie"), filterObj.getSpecie()));
        if(filterObj.getMethod() != null) predicates.add(qb.equal(root.get("method"), filterObj.getMethod()));
        if(filterObj.getColor() != null) predicates.add(qb.equal(root.get("color"), filterObj.getColor()));
        if(filterObj.getUserId() != null) predicates.add(qb.equal(root.get("userId"), filterObj.getUserId()));

        query.select(root).where(qb.and(predicates.toArray(new Predicate[0])));

        return query;
    }
}

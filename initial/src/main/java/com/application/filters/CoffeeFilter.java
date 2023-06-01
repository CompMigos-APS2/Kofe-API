package com.application.filters;

import com.application.entities.Coffee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CoffeeFilter implements Filter<Coffee> {

    public EntityManager em;

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
        query.select(root);

        if(filterObj.getName() != null) query.where(qb.equal(root.get("name"), filterObj.getName()));
        if(filterObj.getSpecie() != null) query.where(qb.equal(root.get("specie"), filterObj.getSpecie()));


        return query;
    }
}

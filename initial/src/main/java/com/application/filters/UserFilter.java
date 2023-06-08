package com.application.filters;

import com.application.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component("UserFilter")
public class UserFilter implements Filter<User> {
    private final EntityManager em;

    public UserFilter(EntityManager em){
        this.em = em;
    }

    @Override
    public CriteriaQuery<User> buildQuery(String req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User filterObj = mapper.readValue(req, User.class);
        List<Predicate> predicates = new ArrayList<>();

        var qb = em.getCriteriaBuilder();
        var query = qb.createQuery(User.class);
        var root = query.from(User.class);

        if(filterObj.getAuthId() != null) predicates.add(qb.equal(root.get("authId"), filterObj.getAuthId()));
        if(filterObj.getName() != null) predicates.add(qb.equal(root.get("name"), filterObj.getName()));
        if(filterObj.getEmail() != null) predicates.add(qb.equal(root.get("email"), filterObj.getEmail()));
        if(filterObj.getLogin() != null) predicates.add(qb.equal(root.get("login"), filterObj.getLogin()));

        query.select(root).where(qb.and(predicates.toArray(new Predicate[0])));

        return query;
    }
}
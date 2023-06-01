package com.application.filters;

import com.application.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Component;

@Component("UserFilter")
public class UserFilter implements Filter<User> {
    private EntityManager em;
    public UserFilter(EntityManager em){
        this.em = em;
    }

    @Override
    public CriteriaQuery<User> buildQuery(String req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User filterObj = mapper.readValue(req, User.class);

        var qb = em.getCriteriaBuilder();
        var query = qb.createQuery(User.class);
        var root = query.from(User.class);
        query.select(root);

        return query;
    }
}

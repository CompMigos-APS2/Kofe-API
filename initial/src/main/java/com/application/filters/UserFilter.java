package com.application.filters;

import com.application.entities.Equipment;
import com.application.entities.Recipe;
import com.application.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Component;

@Component("UserFilter")
public class UserFilter implements Filter<User> {

    @Override
    public CriteriaQuery<User> buildQuery(String req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User effectiveJava = mapper.readValue(req, User.class);
        return null;
    }
}

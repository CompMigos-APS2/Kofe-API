package com.application.filters;

import com.application.entities.Equipment;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public interface Filter<T> {
    CriteriaQuery<T> buildQuery(String jsonReq) throws JsonProcessingException;

}

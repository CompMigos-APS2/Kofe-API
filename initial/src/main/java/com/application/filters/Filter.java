package com.application.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.criteria.CriteriaQuery;

public interface Filter<T> {
    CriteriaQuery<T> buildQuery(String jsonReq) throws JsonProcessingException;
}
package com.application.filters;

import com.application.entities.Equipment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Component;

@Component("EquipmentFilter")
public class EquipmentFilter implements Filter<Equipment> {

    @Override
    public CriteriaQuery<Equipment> buildQuery(String req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Equipment effectiveJava = mapper.readValue(req, Equipment.class);
        return null;
    }
}

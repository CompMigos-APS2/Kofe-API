package com.application.filters;

import com.application.entities.Equipment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Component;

@Component("EquipmentFilter")
public class EquipmentFilter implements Filter<Equipment> {
    private EntityManager em;
    public EquipmentFilter(EntityManager em){
        this.em = em;
    }

    @Override
    public CriteriaQuery<Equipment> buildQuery(String req) throws JsonProcessingException {
        System.out.println(req);
        ObjectMapper mapper = new ObjectMapper();
        Equipment filterObj = mapper.readValue(req, Equipment.class);

        var qb = em.getCriteriaBuilder();
        var query = qb.createQuery(Equipment.class);
        var root = query.from(Equipment.class);
        query.select(root);


        return query;
    }
}

package com.application.filters;

import com.application.entities.Equipment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        List<Predicate> predicates = new ArrayList<>();

        if(filterObj.getInternalId() != null) predicates.add(qb.equal(root.get("id"), filterObj.getInternalId()));
        if(filterObj.getBrand() != null) predicates.add(qb.equal(root.get("brand"), filterObj.getBrand()));
        if(filterObj.getModel() != null) predicates.add(qb.equal(root.get("model"), filterObj.getModel()));
        if(filterObj.getType() != null) predicates.add(qb.equal(root.get("type"), filterObj.getType()));

        query.select(root).where(qb.and(predicates.toArray(new Predicate[0])));

        return query;
    }
}

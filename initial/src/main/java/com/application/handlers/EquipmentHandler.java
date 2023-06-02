package com.application.handlers;

import com.application.entities.Equipment;
import com.application.entities.EquipmentType;
import com.application.filters.EquipmentFilter;
import com.application.repository.EquipmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentHandler extends GenericHandler<Equipment, EquipmentRepository> {
    @Autowired
    public EquipmentHandler(EquipmentRepository repository, EntityManager em) {
        super(repository);
        this.filter = new EquipmentFilter(em);
    }

}


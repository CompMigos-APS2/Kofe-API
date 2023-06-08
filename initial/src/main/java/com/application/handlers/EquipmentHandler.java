package com.application.handlers;

import com.application.entities.Equipment;
import com.application.filters.EquipmentFilter;
import com.application.repository.EquipmentRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
public class EquipmentHandler extends GenericHandler<Equipment, EquipmentRepository> {
    @Autowired
    public EquipmentHandler(EquipmentRepository repository, EntityManager em) {
        super(repository);
        this.filter = new EquipmentFilter(em);
    }
}
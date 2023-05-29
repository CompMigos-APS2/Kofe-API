package com.application.repository;

import java.util.List;
import java.util.UUID;

import com.application.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.application.entities.EquipmentType;

@RepositoryRestResource(collectionResourceRel = "equipment", path = "equipment")
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {
    List<Equipment> findByType(@Param("type") EquipmentType type);

//    @Query(value = "SELECT r.title, r.r_id FROM recipe_equipment re INNER JOIN Recipe r ON re.recipe_id = r.r_id WHERE re.equipment_id = :equipment_id", nativeQuery = true)
//    List<Object> findRecipesWithEquipment(@Param("equipment_id") UUID equipment_id);
}
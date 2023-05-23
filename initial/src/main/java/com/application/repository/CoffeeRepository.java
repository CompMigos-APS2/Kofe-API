package com.application.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.application.entities.Coffee;

@RepositoryRestResource(collectionResourceRel = "coffee", path = "coffee")
public interface CoffeeRepository extends JpaRepository<Coffee, UUID> {
    List<Coffee> findByName(@Param("name") String name);

}
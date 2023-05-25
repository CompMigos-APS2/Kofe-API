package com.application.repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.application.entities.Coffee;
import org.springframework.data.util.Pair;

@RepositoryRestResource(collectionResourceRel = "coffee", path = "coffee")
public interface CoffeeRepository extends JpaRepository<Coffee, UUID> {
    List<Coffee> findByName(@Param("name") String name);

    @Query(value = "SELECT r.title, r.r_id FROM recipe_coffee rc INNER JOIN Recipe r ON rc.recipe_id = r.r_id WHERE rc.coffee_id = :coffee_id", nativeQuery = true)
    List<Object> findRecipesWithCoffee(@Param("coffee_id") UUID coffee_id);
}
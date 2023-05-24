package com.application.repository;

import java.util.List;
import java.util.UUID;

import com.application.entities.Recipe;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recipe", path = "recipe")
@Primary
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    List<Recipe> findByTitle(@Param("title") String title);

    @Query(value = "INSERT INTO recipe_coffee (recipe_id, coffee_id) VALUES (:recipeId, :coffeeId)", nativeQuery = true)
    void insertRecipeCoffee(@Param("recipeId") UUID recipeId, @Param("coffeeId") UUID coffeeId);

}
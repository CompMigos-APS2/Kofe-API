package com.application.repository;

import java.util.UUID;
import com.application.entities.Recipe;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recipe", path = "recipe")
@Primary
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {}
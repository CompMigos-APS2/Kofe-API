package com.application.repository;

import java.util.List;
import java.util.UUID;

import com.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User login(@Param("email") String email, @Param("password") String password);

//    @Query(value = "SELECT r.title, r.r_id FROM recipe_coffee rc INNER JOIN Recipe r ON rc.recipe_id = r.r_id WHERE rc.coffee_id = :coffee_id", nativeQuery = true)
//    List<Object> findRecipesWithCoffee(@Param("coffee_id") UUID coffee_id);
}
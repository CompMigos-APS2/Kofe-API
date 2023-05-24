package com.application.handlers;

import com.application.entities.Coffee;
import com.application.entities.Recipe;
import com.application.repository.CoffeeRepository;

import com.application.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/recipe")
//@Primary
public class RecipeHandler extends GenericHandler<Recipe, RecipeRepository> {
    @Autowired
    public RecipeHandler(RecipeRepository repository) {
        super(repository);
    }
    @RequestMapping("/getByTitle")
    public List<Recipe> getByTitle(String title){
        return repository.findByTitle(title);
    }


    @PostMapping("/save")
    public void save(@RequestBody Recipe obj) {
        // Obter os IDs dos objetos Coffee relacionados
        List<UUID> coffeeIds = obj.getCoffeeIds();

        // Salvar o objeto Recipe
        Recipe savedRecipe = repository.save(obj);
        // Atualizar a associação entre Recipe e Coffee
        for (UUID coffeeId : coffeeIds) {
            repository.insertRecipeCoffee(savedRecipe.getId(), coffeeId);
        }
    }

}

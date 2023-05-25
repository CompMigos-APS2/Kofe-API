package com.application.handlers;

import com.application.entities.Coffee;
import com.application.entities.Recipe;
import com.application.repository.CoffeeRepository;

import com.application.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private CoffeeRepository coffeeRepository;
    @PostMapping("/save")
    public ResponseEntity<Recipe> save(@RequestBody Recipe obj) {
        // Obter os IDs dos objetos Coffee relacionados
        List<String> coffeeStringIds = obj.getCoffeeStringIds();
        for(String coffeeStringId : coffeeStringIds) {
            Optional<Coffee> coffee = coffeeRepository.findById(UUID.fromString(coffeeStringId));
            if(coffee.isEmpty())
                continue;
            obj.addCoffeeUsed(coffee.get());
        }
        Recipe savedRecipe = repository.save(obj);
        // Salvar o objeto Recipe
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }
}

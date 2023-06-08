package com.application.handlers;

import com.application.entities.Equipment;
import com.application.entities.User;
import com.application.entities.Coffee;
import com.application.entities.Recipe;
import com.application.exceptions.NotFoundException;
import com.application.filters.UserFilter;
import com.application.repository.EquipmentRepository;
import com.application.repository.RecipeRepository;
import com.application.repository.UserRepository;
import com.application.repository.CoffeeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserHandler extends GenericHandler<User, UserRepository> {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private StatsHandler statsHandler;

    @Autowired
    public UserHandler(UserRepository repository, EntityManager em) {
        super(repository);
        this.filter = new UserFilter(em);
    }

    @Validated
    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User obj) {
        List<UUID> equipmentIds = obj.getEquipmentIds();
        equipmentIds.forEach(equipmentId -> equipmentRepository.findById(equipmentId)
                .ifPresentOrElse(
                    equipment -> {obj.addEquipment(equipment);},
                    () -> { throw new NotFoundException("Equipment not found"); }
                ));
        try {
            User savedUser = repository.save(obj);
            statsHandler.setUserListUpdated(true);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Constraint violation occurred");
        }
    }
    @RequestMapping("/getCoffees")
    public ResponseEntity<List<Coffee>> getCoffees(String id){ return handleRelatedEntities(id, User::getCoffeesIds, coffeeRepository::findAllById); }

    @RequestMapping("/getEquipments")
    public ResponseEntity<List<Equipment>> getEquipments(String id){ return handleRelatedEntities(id, User::getEquipmentIds, equipmentRepository::findAllById); }
    @RequestMapping("/getRecipes")
    public ResponseEntity<List<Recipe>> getRecipes(String id) { return handleRelatedEntities(id, User::getRecipesIds, recipeRepository::findAllById); }

    private <T> ResponseEntity<List<T>> handleRelatedEntities(String id, IdGetter<UUID> idGetter, EntityFetcher<UUID, T> entityFetcher) {
        UUID formattedId = UUID.fromString(id);

        User user = repository.findById(formattedId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<UUID> entityIds = idGetter.getIds(user);
        List<T> entities = entityFetcher.fetchEntities(entityIds);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @FunctionalInterface
    private interface IdGetter<T> { List<T> getIds(User user); }

    @FunctionalInterface
    private interface EntityFetcher<T, R> { List<R> fetchEntities(List<T> entityIds); }
}

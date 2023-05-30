package com.application.handlers;

import com.application.entities.Equipment;
import com.application.entities.EquipmentType;
import com.application.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipment")
public class EquipmentHandler extends GenericHandler<Equipment, EquipmentRepository> {
    @Autowired
    public EquipmentHandler(EquipmentRepository repository) {
        super(repository);
    }
    @RequestMapping("/getByType")
    public ResponseEntity<List<Equipment>> getByType(EquipmentType type){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
        
        return new ResponseEntity<>(repository.findByType(type), headers, HttpStatus.OK);
    }


//    @RequestMapping("/deleteEquipmentById")
//    public ResponseEntity<List<Object>> deleteEquipmentById(String id) {
//
//        UUID formattedId = UUID.fromString(id);
//        List<Object> recipeList = repository.findRecipesWithEquipment(formattedId);
//        if(recipeList.size() == 0) {
//            repository.deleteById(formattedId);
//            return new ResponseEntity<>(recipeList, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(recipeList, HttpStatus.CONFLICT);
//    }

}

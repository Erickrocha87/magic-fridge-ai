package com.rocha.MagicFridgeAi.controllers;

import com.rocha.MagicFridgeAi.entity.Food;
import com.rocha.MagicFridgeAi.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fridgeai/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<Food>> findAll(){
        return ResponseEntity.ok().body(foodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> findByid(@PathVariable Long id){
        return ResponseEntity.ok().body(foodService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Food> saveFood(@RequestBody Food food){
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.saveFood(food));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food food){
        return ResponseEntity.ok().body(foodService.updateFood(id, food));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBId(@PathVariable Long id){
        foodService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

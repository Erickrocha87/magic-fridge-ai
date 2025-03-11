package com.rocha.MagicFridgeAi.controllers;

import com.rocha.MagicFridgeAi.entity.Food;
import com.rocha.MagicFridgeAi.services.ChatGptService;
import com.rocha.MagicFridgeAi.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/fridgeai")
@RequiredArgsConstructor
public class RecipeController {

    private  final FoodService foodService;
    private final ChatGptService chatGptService;

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe(){
        List<Food> foods = foodService.findAll();
        return chatGptService.generateRecipe(foods)
                .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.noContent().build());
    }
}

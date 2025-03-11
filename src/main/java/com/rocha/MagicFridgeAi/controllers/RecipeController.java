package com.rocha.MagicFridgeAi.controllers;

import com.rocha.MagicFridgeAi.entity.Food;
import com.rocha.MagicFridgeAi.services.ChatGptService;
import com.rocha.MagicFridgeAi.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fridgeai")
@RequiredArgsConstructor
public class RecipeController {

    private  final FoodService foodService;
    private final ChatGptService chatGptService;

    @GetMapping("/informations")
    public String generateRecipe(){
        List<Food> foods = foodService.findAll();
        return chatGptService.generateRecipe(foods);
    }
}

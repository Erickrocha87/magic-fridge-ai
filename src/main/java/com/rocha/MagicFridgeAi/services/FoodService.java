package com.rocha.MagicFridgeAi.services;

import com.rocha.MagicFridgeAi.entity.Food;
import com.rocha.MagicFridgeAi.repositories.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<Food> findAll(){
        return foodRepository.findAll();
    }

    public Food findById(Long id){
        return foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find food with id: " + id));
    }

    public Food saveFood(Food food){
        return foodRepository.save(food);
    }

    public void deleteById(Long id){
        foodRepository.deleteById(id);
    }

    public Food updateFood(Long id, Food food){
        Optional<Food> updatedFood = foodRepository.findById(id);
        if (updatedFood.isPresent()) {
            Food savedFood = new Food();
            savedFood.setId(id);
            savedFood.setName(food.getName());
            savedFood.setCategory(food.getCategory());
            savedFood.setQuantity(food.getQuantity());
            savedFood.setValidate(food.getValidate());
            return foodRepository.save(savedFood);
        }
        return null;
    }
}

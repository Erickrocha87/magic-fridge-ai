package com.rocha.MagicFridgeAi.repositories;

import com.rocha.MagicFridgeAi.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}

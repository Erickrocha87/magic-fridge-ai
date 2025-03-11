package com.rocha.MagicFridgeAi.services;

import com.rocha.MagicFridgeAi.entity.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatGptService {

    private final OpenAiChatModel chatClient;



    public String generateRecipe(List<Food> foodsRequest){
        String foods = foodsRequest.stream()
                .map(food -> String.format("%s (%s) - Quantity: %d, Validate: %s",
                        food.getName(), food.getCategory(), food.getQuantity(), food.getValidate()))
                .collect(Collectors.joining("\n"));

        String prompt = "Based on the following foods, suggest a creative and practical recipe" + foods;
        return chatClient.call(prompt);
    }
}
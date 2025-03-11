package com.rocha.MagicFridgeAi.services;

import com.rocha.MagicFridgeAi.entity.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public Mono<String> generateRecipe(List<Food> foodsRequest) {

        String foods = foodsRequest.stream()
                .map(food -> String.format("%s (%s) - Quantity: %d, Validate: %s",
                        food.getName(), food.getCategory(), food.getQuantity(), food.getValidate()))
                .collect(Collectors.joining("\n"));

        String prompt = "Based on the following foods, suggest a creative and practical recipe" + foods;
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a kitchen assistant who creates recipes"),
                        Map.of("role", "user", "content", prompt)
                )
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String,Object> ) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                   return "Any receive was generated";
                });
    }
}
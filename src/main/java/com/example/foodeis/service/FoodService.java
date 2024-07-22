package com.example.foodeis.service;

import com.example.foodeis.model.Category;
import com.example.foodeis.model.Food;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.request.CreateFoodRequest;
//import com.example.foodeis.exception.FoodNotFoundException;

import java.util.List;

public interface FoodService {
    Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception;

    void deleteFood(Long foodId) throws Exception;

    List<Food> getAllFood(Long restaurantId, boolean isVegetarian, boolean isNonVegetarian, boolean isSeasonal, String foodCategory) throws Exception;

    List<Food> searchFood(String keyword) throws Exception;

    Food findFoodById(Long id) throws Exception;

    Food updateAvailabilityStatus(Long id) throws Exception;
}

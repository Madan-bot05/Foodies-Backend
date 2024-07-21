package com.example.foodeis.service;

import com.example.foodeis.model.Category;
import com.example.foodeis.model.Food;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception;

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getAllFood(Long restaurantId,boolean isVegetarian,boolean isSeasonal,String foodCategory) throws Exception;

    public List<Food> searchFood(String keyword) throws Exception;

    public Food findFoodById(Long id) throws Exception;

    public Food updateAvailabilityStatus(Long id) throws Exception;
}

package com.example.foodeis.service;

import com.example.foodeis.model.IngredientsCategory;
import com.example.foodeis.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    public IngredientsCategory createIngredientsCategory(String name,Long RestaurantId) throws Exception;
    public IngredientsCategory findIngredientCategoryById(Long id) throws Exception;
    public List< IngredientsCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
    public IngredientsItem createIngredientsItem(String name,Long RestaurantId,Long categoryId) throws Exception;
    public List<IngredientsCategory> findRestaurantIngredients(Long id) throws Exception;
    public IngredientsItem updateIngredientsItem(Long id) throws Exception;

}

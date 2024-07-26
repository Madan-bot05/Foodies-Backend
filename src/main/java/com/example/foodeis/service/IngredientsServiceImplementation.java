package com.example.foodeis.service;


import com.example.foodeis.model.IngredientsCategory;
import com.example.foodeis.model.IngredientsItem;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.repository.IngredientItemRepository;
import com.example.foodeis.repository.IngredinetsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImplementation implements IngredientsService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredinetsCategoryRepository ingredinetsCategoryRepository;
    @Autowired
    private ResturantService resturantService;

    @Override
    public IngredientsCategory createIngredientsCategory(String name, Long RestaurantId) throws Exception {
        Restaurant restaurant=resturantService.findResturantById(RestaurantId);
        IngredientsCategory ingredientsCategory=new IngredientsCategory();
        ingredientsCategory.setRestaurant(restaurant);
        ingredientsCategory.setName(name);

        return ingredinetsCategoryRepository.save(ingredientsCategory);
    }

    @Override
    public IngredientsCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> ingredientsCategory=ingredinetsCategoryRepository.findById(id);
        if(ingredientsCategory.isEmpty()){
            throw new Exception("Ingretdients not found");
        }
        return ingredientsCategory.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        return List.of();
    }

    @Override
    public IngredientsItem createIngredientsItem(String name, Long RestaurantId, Long categoryId) throws Exception {
        return null;
    }

    @Override
    public List<IngredientsCategory> findRestaurantIngredients(Long id) throws Exception {
        return List.of();
    }

    @Override
    public IngredientsItem updateIngredientsItem(Long id) throws Exception {
        return null;
    }
}

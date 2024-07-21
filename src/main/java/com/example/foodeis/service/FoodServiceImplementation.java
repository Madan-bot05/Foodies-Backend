package com.example.foodeis.service;

import com.example.foodeis.model.Category;
import com.example.foodeis.model.Food;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.repository.FoodRespository;
import com.example.foodeis.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImplementation  implements FoodService{
    @Autowired
    private FoodRespository foodRespository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setPrice(req.getPrice());
        food.setName(req.getName());
        food.setIngredientsItems(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegeterian());

        Food savedFood = foodRespository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setRestaurant(null);
        foodRespository.save(food);
    }



//@Override
//public List<Food> getAllFood(Long restaurantId, boolean isVegetarian, boolean isNonVegetarian, boolean isSeasonal, String foodCategory) throws Exception {
//
//        List<Food> foods=foodRespository.finByRestaurantId(restaurantId);
//
//        if(isVegetarian){
////            foods=filterByVegeterian(foods,isVegetarian);
//        }
//        if(isSeasonal){}
//        if(isNonVegetarian)
//        return foods;
//
//    }

//    private List<Food> filterByVegeterian(List<Food> foods, boolean isVegetarian) {
//        return new List<>();
//    }

    @Override
    public List<Food> searchFood(String keyword) throws Exception {
        return List.of();
    }

    @Override
    public Food findFoodById(Long id) throws Exception {
        return null;
    }

    @Override
    public Food updateAvailabilityStatus(Long id) throws Exception {
        return null;
    }
}

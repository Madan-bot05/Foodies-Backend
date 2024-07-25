package com.example.foodeis.service;

import com.example.foodeis.model.Category;
import com.example.foodeis.model.Food;
import com.example.foodeis.model.IngredientsItem;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.repository.CategoryRepository;
import com.example.foodeis.repository.FoodRespository;
import com.example.foodeis.repository.ResturantRepository;
import com.example.foodeis.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImplementation  implements FoodService{
    @Autowired
    private FoodRespository foodRespository;
    @Autowired
    private ResturantRepository resturantRepository;
    @Autowired
    private CategoryRepository categoryRepository;

//    @Override
//    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception {
//        Food food = new Food();
//        food.setFoodCategory(category);
//        food.setRestaurant(restaurant);
//        food.setDescription(req.getDescription());
//        food.setImages(req.getImages());
//        food.setPrice(req.getPrice());
//        food.setName(req.getName());
//        food.setIngredientsItems(req.getIngredients());
//        food.setSeasonal(req.isSeasonal());
//        food.setVegetarian(req.isVegeterian());
//
//        Food savedFood = foodRespository.save(food);
//        restaurant.getFoods().add(savedFood);
//        return savedFood;
//    }
@Transactional
public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception {
    // Save category if it's not already persisted
    if (category.getId() == null) {
        category = categoryRepository.save(category);
    } else if (!categoryRepository.existsById(category.getId())) {
        throw new Exception("Category ID " + category.getId() + " does not exist.");
    }

    // Save restaurant if it's not already persisted
    if (restaurant.getId() == null) {
        restaurant = resturantRepository.save(restaurant);
    } else if (!resturantRepository.existsById(restaurant.getId())) {
        throw new Exception("Restaurant ID " + restaurant.getId() + " does not exist.");
    }

    // Save ingredients if they are not already persisted
    List<IngredientsItem> persistedIngredients = new ArrayList<>();
//    for (IngredientsItem ingredient : req.getIngredients()) {
//        if (ingredient.getId() == null) {
//            ingredient = ingredientsItemRepository.save(ingredient);
//        } else if (!ingredientsItemRepository.existsById(ingredient.getId())) {
//            throw new Exception("Ingredient ID " + ingredient.getId() + " does not exist.");
//        }
//        persistedIngredients.add(ingredient);
//    }

    Food food = new Food();
    food.setFoodCategory(category);
    food.setRestaurant(restaurant);
    food.setDescription(req.getDescription());
    food.setImages(req.getImages());
    food.setPrice(req.getPrice());
    food.setName(req.getName());
    food.setIngredientsItems(persistedIngredients);
    food.setSeasonal(req.isSeasonal());
    food.setVegetarian(req.isVegeterian());
    food.setAvailable(true);  // Assuming new food items are available by default
    food.setCreationDate(new Date());

    Food savedFood = foodRespository.save(food);

    // Add the new food to the restaurant's food list
    if (restaurant.getFoods() == null) {
        restaurant.setFoods(new ArrayList<>());
    }
    restaurant.getFoods().add(savedFood);

    return savedFood;
}

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setRestaurant(null);
        foodRespository.save(food);
    }

    @Override
    public List<Food> getAllFood(Long restaurantId, boolean isVegetarian, boolean isNonVegetarian, boolean isSeasonal, String foodCategory) throws Exception {
        List<Food> foods=foodRespository.findByRestaurantId(restaurantId);

        if(isVegetarian){ foods=filterByVegeterian(foods,isVegetarian);}
        if(isNonVegetarian){ foods=filterByNonVeg(foods,isNonVegetarian);}
        if(isSeasonal){foods=filterBySeasonal(foods,isSeasonal);}
        if(foodCategory!=null && !foodCategory.equals("")){ foods=filterByCategory(foods,foodCategory);}

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegeterian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian()==isVegetarian).collect(Collectors.toList());
    }


    @Override
    public List<Food> searchFood(String keyword) throws Exception {
        return foodRespository.searchFoodBy(keyword);
    }

    @Override
    public Food findFoodById(Long id) throws Exception {
        Optional<Food> food=foodRespository.findById(id);
        if(food.isEmpty()){ throw new Exception("Food Not Exists");}

        return food.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long id) throws Exception {
        Food food=findFoodById(id);
        food.setAvailable(!food.isAvailable());
        return foodRespository.save(food);

    }
}

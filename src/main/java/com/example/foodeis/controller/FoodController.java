package com.example.foodeis.controller;


import com.example.foodeis.model.Food;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.model.User;
import com.example.foodeis.request.CreateFoodRequest;
import com.example.foodeis.service.FoodService;
import com.example.foodeis.service.ResturantService;
import com.example.foodeis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResturantService resturantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword, @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Food> food=foodService.searchFood(keyword);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long restaurantId
            ,@RequestParam boolean vegeterian
            ,@RequestParam boolean seasonal
            ,@RequestParam boolean nonVegeterian,
            @RequestParam(required = false) String food_category
            , @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Food> food=foodService.getAllFood(restaurantId,vegeterian,nonVegeterian,seasonal,food_category);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

}

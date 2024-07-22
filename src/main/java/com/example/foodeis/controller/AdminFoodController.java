package com.example.foodeis.controller;


import com.example.foodeis.model.Food;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.model.User;
import com.example.foodeis.request.CreateFoodRequest;
import com.example.foodeis.response.MessageResponse;
import com.example.foodeis.service.FoodService;
import com.example.foodeis.service.ResturantService;
import com.example.foodeis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private  FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResturantService resturantService;

    @PostMapping("/")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=resturantService.findResturantById(req.getRestaurantId());
        Food food=foodService.createFood(req,req.getCategory(),restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Food> deleteFood(@PathVariable Long foodId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        foodService.deleteFood(foodId);
        MessageResponse res=new MessageResponse();
        res.setMessage("Deleted Food");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long foodId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Food food=foodService.updateAvailabilityStatus(foodId);
//        MessageResponse res=new MessageResponse();
//        res.setMessage("Deleted Food");
        return new ResponseEntity<>(food,HttpStatus.OK);
    }

}

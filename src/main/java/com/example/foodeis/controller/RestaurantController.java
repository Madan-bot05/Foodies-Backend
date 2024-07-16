package com.example.foodeis.controller;

import com.example.foodeis.model.Restaurant;
import com.example.foodeis.model.User;
import com.example.foodeis.request.CreateResturantRequest;
import com.example.foodeis.service.ResturantService;
import com.example.foodeis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private ResturantService resturantService;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant=resturantService.searchResturants(keyword);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant=resturantService.searchResturants(keyword);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
}

package com.example.foodeis.controller;

import com.example.foodeis.model.Restaurant;
import com.example.foodeis.model.User;
import com.example.foodeis.request.CreateResturantRequest;
import com.example.foodeis.service.ResturantService;
import com.example.foodeis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private ResturantService resturantService;
    @Autowired
    private UserService userService;

    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateResturantRequest req, @RequestHeader ("Authorization") String jwt) throws Exception {
            User user=userService.findUserByJwtToken(jwt);
            Restaurant restaurant=resturantService.createResturant(req,user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
}

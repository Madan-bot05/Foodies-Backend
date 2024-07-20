package com.example.foodeis.controller;

import com.example.foodeis.model.Restaurant;
import com.example.foodeis.model.User;
import com.example.foodeis.request.CreateResturantRequest;
import com.example.foodeis.response.MessageResponse;
import com.example.foodeis.service.ResturantService;
import com.example.foodeis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private ResturantService resturantService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateResturantRequest req, @RequestHeader ("Authorization") String jwt) throws Exception {
            User user=userService.findUserByJwtToken(jwt);
            Restaurant restaurant=resturantService.createResturant(req,user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateResturantRequest req, @RequestHeader ("Authorization") String jwt,@PathVariable Long id) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=resturantService.updateResturant(id, req);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@RequestHeader ("Authorization") String jwt,@PathVariable Long id) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        resturantService.deleteResturant(id);

        MessageResponse res=new MessageResponse();
        res.setMessage("Restaurant deleted successfully");

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus( @RequestHeader ("Authorization") String jwt,@PathVariable Long id) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=resturantService.updateResturantStatus(id);

        return new ResponseEntity<>(restaurant,HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId( @RequestHeader ("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=resturantService.getResturantByUserId(user.getId());

        MessageResponse res=new MessageResponse();
        res.setMessage("Restaurant deleted successfully");

        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
}



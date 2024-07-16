package com.example.foodeis.service;

import com.example.foodeis.dto.RestaurantDto;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.model.User;
import com.example.foodeis.request.CreateResturantRequest;

import java.util.List;

public interface ResturantService {
    public Restaurant createResturant(CreateResturantRequest req, User user);
    public Restaurant updateResturant(Long resturantId,CreateResturantRequest updatedResturant) throws Exception;
    public void deleteResturant(Long resturantId) throws Exception;
    public Restaurant findResturantById(Long Id) throws Exception;
    public List<Restaurant> getAllResturants() throws Exception;
    public List<Restaurant> searchResturants(String keyword) throws Exception;
    public Restaurant getResturantByUserId(Long userId) throws Exception;
    public RestaurantDto addtoFavourites(Long resturantId, User user) throws Exception;
    public Restaurant updateResturantStatus(Long resturantId) throws Exception;
}

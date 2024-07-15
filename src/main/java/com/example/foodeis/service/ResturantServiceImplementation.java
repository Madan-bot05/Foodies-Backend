package com.example.foodeis.service;

import com.example.foodeis.dto.RestaurantDto;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.model.User;
import com.example.foodeis.request.CreateResturantRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResturantServiceImplementation implements ResturantService{
    @Override
    public Restaurant createResturant(CreateResturantRequest req, User user) {
        return null;
    }

    @Override
    public Restaurant updateResturant(Long resturantId, CreateResturantRequest updatedResturant) throws Exception {
        return null;
    }

    @Override
    public void deleteResturant(Long resturantId) throws Exception {

    }

    @Override
    public Restaurant findResturant(Long Id) throws Exception {
        return null;
    }

    @Override
    public List<Restaurant> getAllResturants() throws Exception {
        return List.of();
    }

    @Override
    public List<Restaurant> searchResturants() throws Exception {
        return List.of();
    }

    @Override
    public Restaurant getResturantByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public RestaurantDto addtoFavourites(Long resturantId, User user) throws Exception {
        return null;
    }
}

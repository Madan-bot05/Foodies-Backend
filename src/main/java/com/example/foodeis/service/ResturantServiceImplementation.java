package com.example.foodeis.service;

import com.example.foodeis.dto.RestaurantDto;
import com.example.foodeis.model.Address;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.model.User;
import com.example.foodeis.repository.AddressRepository;
import com.example.foodeis.repository.ResturantRepository;
import com.example.foodeis.repository.UserRepository;
import com.example.foodeis.request.CreateResturantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResturantServiceImplementation implements ResturantService{

    @Autowired
    private ResturantRepository resturantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createResturant(CreateResturantRequest req, User user) {
        Address address=addressRepository.save(req.getAddress());
        Restaurant restaurant=new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours((req.getOpeningHours()));
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return resturantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateResturant(Long resturantId, CreateResturantRequest updatedResturant) throws Exception {
        Restaurant restaurant= findResturantById(resturantId);
        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedResturant.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedResturant.getDescription());
        }
        if(restaurant.getImages()!=null){
            restaurant.setImages(updatedResturant.getImages());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(updatedResturant.getName());
        }
        if(restaurant.getOpeningHours()!=null){
            restaurant.setOpeningHours(updatedResturant.getOpeningHours());
        }
        if(restaurant.getAddress()!=null){
            restaurant.setAddress(updatedResturant.getAddress());
        }
        if(restaurant.getContactInformation()!=null){
            restaurant.setContactInformation(updatedResturant.getContactInformation());
        }

        return resturantRepository.save(restaurant);
    }

    @Override
    public void deleteResturant(Long resturantId) throws Exception {
        Restaurant restaurant=findResturantById(resturantId);
            resturantRepository.delete(restaurant);
    }

    @Override
    public Restaurant findResturantById(Long Id) throws Exception {
        Optional<Restaurant> restaurant=resturantRepository.findById(Id);
        if (restaurant.isEmpty()){
            throw new Exception("Restuarant Not Found"+Id);
        }
        return restaurant.get();
    }

    @Override
    public List<Restaurant> getAllResturants() throws Exception {
        return resturantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchResturants(String keyword) throws Exception {
        return resturantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant getResturantByUserId(Long userId) throws Exception {
        Restaurant restaurant=resturantRepository.findAllByOwnerId(userId);
        if (restaurant==null){
            throw new Exception("Restaurant Not Found");
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addtoFavourites(Long resturantId, User user) throws Exception {
        Restaurant restaurant=findResturantById(resturantId);
        RestaurantDto dto=new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(resturantId);

        boolean isFavourites=false;
        List<RestaurantDto> favourites=user.getFavourites();
        for(RestaurantDto favourite:favourites){
            if (favourite.getId()==resturantId){
                isFavourites=true;
                break;
            }
        }

        if(isFavourites){
            favourites.removeIf(favourite -> favourite.getId()==resturantId);
        }else {
            favourites.add(dto);
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateResturantStatus(Long resturantId) throws Exception {
        Restaurant restaurant=findResturantById(resturantId);
//        restaurant.setOpen(restaurant.isOpen());
        if(restaurant.isOpen()==true ){
            restaurant.setOpen(false);
        }else
            restaurant.setOpen(true);
        return resturantRepository.save(restaurant);
    }
}

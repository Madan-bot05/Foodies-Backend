package com.example.foodeis.request;

import com.example.foodeis.model.Address;
import com.example.foodeis.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateResturantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private String phone;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}

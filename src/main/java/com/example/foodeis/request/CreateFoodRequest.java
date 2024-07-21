package com.example.foodeis.request;


import com.example.foodeis.model.Category;
import com.example.foodeis.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegeterian;
    private boolean seasonal;
    private List<IngredientsItem> ingredients;

}

package com.example.foodeis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id") // Explicitly define the join column
    private Category foodCategory;

    @ElementCollection
    @CollectionTable(name = "food_images", joinColumns = @JoinColumn(name = "food_id")) // Define a separate table for images
    @Column(name = "image_url", length = 1000)
    private List<String> images = new ArrayList<>();

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Explicitly define the join column
    private Restaurant restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;

    @ManyToMany
    @JoinTable(
            name = "food_ingredients",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<IngredientsItem> ingredientsItems = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}

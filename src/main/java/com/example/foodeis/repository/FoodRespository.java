package com.example.foodeis.repository;

import com.example.foodeis.model.Food;
import com.example.foodeis.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRespository extends JpaRepository<Food, Long> {
    List<Food> finByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f where f.name LIKE %:keyword% OR f.foodCategory.name like %:keyword%")
    List<Food> searchFoodBy(@Param("keyword") String keyword);
}

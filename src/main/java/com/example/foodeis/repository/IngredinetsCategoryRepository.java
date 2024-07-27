package com.example.foodeis.repository;

import com.example.foodeis.model.IngredientsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredinetsCategoryRepository extends JpaRepository<IngredientsCategory,Long> {
    List<IngredientsCategory> findAllByRestaurantId(Long id);
}

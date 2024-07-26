package com.example.foodeis.repository;

import com.example.foodeis.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem,Long> {
}

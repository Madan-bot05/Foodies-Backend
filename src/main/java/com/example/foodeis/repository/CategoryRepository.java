package com.example.foodeis.repository;

import com.example.foodeis.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findAllByRestaurantId(Long id);
}

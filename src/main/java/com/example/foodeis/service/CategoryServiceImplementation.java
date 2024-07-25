package com.example.foodeis.service;

import com.example.foodeis.model.Category;
import com.example.foodeis.model.Restaurant;
import com.example.foodeis.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{
    @Autowired
    private ResturantService resturantService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant=resturantService.getResturantByUserId(userId);
        Category category=new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurant(Long id) throws Exception {
        Restaurant restaurant=resturantService.getResturantByUserId(id);
        return categoryRepository.findAllByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category=categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new Exception("Category Not Found");
        }
        return category.get();
    }
}

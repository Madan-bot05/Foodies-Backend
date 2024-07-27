package com.example.foodeis.controller;


import com.example.foodeis.model.IngredientsCategory;
import com.example.foodeis.model.IngredientsItem;
import com.example.foodeis.request.IngredientsCategoryRequest;
import com.example.foodeis.request.IngredientsRequest;
import com.example.foodeis.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    private ResponseEntity<IngredientsCategory> createIngredientsCategory(@RequestBody IngredientsCategoryRequest req) throws Exception {
        IngredientsCategory item=ingredientsService.createIngredientsCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    private ResponseEntity<IngredientsItem> createIngredientsItem(@RequestBody IngredientsRequest req) throws Exception {
        IngredientsItem item=ingredientsService.createIngredientsItem(req.getName(),req.getRestaurantId(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    private ResponseEntity<IngredientsItem> updateIngredientsStock(@PathVariable Long id) throws Exception {
        IngredientsItem item=ingredientsService.updateIngredientsItem(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

//    @GetMapping("/restaurant/{id}")
//    private ResponseEntity< List<IngredientsItem>> getRestaurantIngredients(@PathVariable Long id) throws Exception {
//        List<IngredientsItem> items=ingredientsService.findRestaurantIngredients(id);
//        return new ResponseEntity<>(items, HttpStatus.OK);
//    }

    @GetMapping("/restuarant/{id}/category")
    private ResponseEntity<List<IngredientsCategory> > getIngredientsCategory(@PathVariable Long id) throws Exception {
        List<IngredientsCategory> item=ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}

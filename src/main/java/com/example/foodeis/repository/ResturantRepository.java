package com.example.foodeis.repository;

import com.example.foodeis.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResturantRepository extends JpaRepository<Restaurant,Long> {

    @Query("SELECT r from Restaurant  r where lower(r.name) LIKE lower(concat('%',:query,'%'))"+
    "OR lower(r.cuisineType) LIKE lower(concat('%',:query,'%'))")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findAllByOwnerId(long id);

}

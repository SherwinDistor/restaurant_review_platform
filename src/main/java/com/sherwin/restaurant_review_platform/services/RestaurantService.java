package com.sherwin.restaurant_review_platform.services;

import java.util.List;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;

public interface RestaurantService {
    List<RestaurantDto> getAllRestaurants();

    RestaurantDto createRestaurant(CreateRestaurantDto createRestaurantDto);

    RestaurantDto getRestaurantById(Integer id);
}

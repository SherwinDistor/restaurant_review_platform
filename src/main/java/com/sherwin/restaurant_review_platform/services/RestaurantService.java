package com.sherwin.restaurant_review_platform.services;

import java.util.List;
import java.util.UUID;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;

public interface RestaurantService {
    List<RestaurantDto> getAllRestaurants();

    RestaurantDto createRestaurant(CreateRestaurantDto createRestaurantDto);

    RestaurantDto getRestaurantById(UUID id);

    RestaurantDto updateRestaurant(UUID id, CreateRestaurantDto createRestaurantDto);

    void deleteRestaurant(UUID id);
}

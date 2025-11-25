package com.sherwin.restaurant_review_platform.mappers.impl;

import org.springframework.stereotype.Component;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.mappers.RestaurantMapper;

@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public RestaurantDto toDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setCuisineType(restaurant.getCuisineType());
        restaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDto.setAverageRating(restaurant.getAverageRating());

        return restaurantDto;
    }

}

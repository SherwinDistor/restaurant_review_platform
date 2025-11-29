package com.sherwin.restaurant_review_platform.mappers;

import java.util.List;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;

public interface RestaurantMapper {
    RestaurantDto toDto(Restaurant restaurant);

    List<RestaurantDto> toListDto(List<Restaurant> restaurants);
}

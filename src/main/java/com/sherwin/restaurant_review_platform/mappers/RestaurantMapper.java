package com.sherwin.restaurant_review_platform.mappers;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;

public interface RestaurantMapper {
    RestaurantDto toDto(Restaurant restaurant);
}

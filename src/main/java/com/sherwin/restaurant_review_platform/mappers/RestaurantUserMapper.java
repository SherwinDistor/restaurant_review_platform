package com.sherwin.restaurant_review_platform.mappers;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;

public interface RestaurantUserMapper {
  RestaurantUserDto toDto(RestaurantUser restaurantUser);
}

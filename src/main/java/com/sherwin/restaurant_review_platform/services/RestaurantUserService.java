package com.sherwin.restaurant_review_platform.services;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;

public interface RestaurantUserService {

  RestaurantUserDto getUserProfile(String username);
}

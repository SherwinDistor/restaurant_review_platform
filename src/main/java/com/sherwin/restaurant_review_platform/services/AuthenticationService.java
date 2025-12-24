package com.sherwin.restaurant_review_platform.services;

import com.sherwin.restaurant_review_platform.domain.dtos.LoginRequestDto;
import com.sherwin.restaurant_review_platform.domain.dtos.LoginResponseDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RegisterRestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;

public interface AuthenticationService {

  RestaurantUserDto registerUser(RegisterRestaurantUserDto registerRestaurantUserDto);

  LoginResponseDto loginUser(LoginRequestDto loginRequestDto);
}

package com.sherwin.restaurant_review_platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;
import com.sherwin.restaurant_review_platform.services.RestaurantUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class RestaurantUserController {

  private final RestaurantUserService restaurantUserService;

  @GetMapping("/profile")
  public ResponseEntity<RestaurantUserDto> getProfile(Authentication authentication) {
    RestaurantUserDto restaurantUserDto = restaurantUserService.getUserProfile(authentication.getName());

    return new ResponseEntity<>(restaurantUserDto, HttpStatus.OK);
  }
}

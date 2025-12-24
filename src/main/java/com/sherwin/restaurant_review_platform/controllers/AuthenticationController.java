package com.sherwin.restaurant_review_platform.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherwin.restaurant_review_platform.domain.dtos.LoginRequestDto;
import com.sherwin.restaurant_review_platform.domain.dtos.LoginResponseDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RegisterRestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;
import com.sherwin.restaurant_review_platform.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public RestaurantUserDto registerUser(@RequestBody RegisterRestaurantUserDto registerRestaurantUserDto) {
    RestaurantUserDto registeredUser = authenticationService.registerUser(registerRestaurantUserDto);

    return registeredUser;
  }

  @PostMapping("/login")
  public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
    LoginResponseDto loginUser = authenticationService.loginUser(loginRequestDto);

    return loginUser;
  }

}

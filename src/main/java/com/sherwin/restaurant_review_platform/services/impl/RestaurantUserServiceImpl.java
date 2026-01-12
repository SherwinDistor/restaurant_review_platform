package com.sherwin.restaurant_review_platform.services.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.mappers.RestaurantUserMapper;
import com.sherwin.restaurant_review_platform.repositories.RestaurantUserRepository;
import com.sherwin.restaurant_review_platform.services.RestaurantUserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantUserServiceImpl implements UserDetailsService, RestaurantUserService {

  private final RestaurantUserRepository restaurantUserRepository;
  private final RestaurantUserMapper restaurantUserMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    System.out.println("In the user details service");

    Optional<RestaurantUser> restaurantUserOp = restaurantUserRepository.findByUsername(username);

    if (restaurantUserOp.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    return restaurantUserOp.get();
  }

  @Override
  public RestaurantUserDto getUserProfile(String username) {
    Optional<RestaurantUser> restaurantUserOp = restaurantUserRepository.findByUsername(username);

    if (restaurantUserOp.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    RestaurantUserDto restaurantUserDto = restaurantUserMapper.toDto(restaurantUserOp.get());

    return restaurantUserDto;
  }

}

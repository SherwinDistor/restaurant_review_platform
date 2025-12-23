package com.sherwin.restaurant_review_platform.services.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.repositories.RestaurantUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantUserServiceImpl implements UserDetailsService {

  private final RestaurantUserRepository restaurantUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    System.out.println("In the user details service");

    Optional<RestaurantUser> restaurantUserOp = restaurantUserRepository.findByUsername(username);

    if (restaurantUserOp.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    return restaurantUserOp.get();
  }

}

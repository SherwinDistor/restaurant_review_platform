package com.sherwin.restaurant_review_platform.services.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sherwin.restaurant_review_platform.domain.dtos.RegisterRestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.domain.entities.Role;
import com.sherwin.restaurant_review_platform.repositories.RestaurantUserRepository;
import com.sherwin.restaurant_review_platform.repositories.RoleRepository;
import com.sherwin.restaurant_review_platform.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final RestaurantUserRepository restaurantUserRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public RestaurantUserDto registerUser(RegisterRestaurantUserDto registerRestaurantUserDto) {
    Optional<RestaurantUser> userOp = restaurantUserRepository
        .findByUsername(registerRestaurantUserDto.getUsername());

    if (userOp.isPresent()) {
      // TODO: create runtime exception to handle users
      throw new RuntimeException("Unable to register user");
    }

    Optional<Role> roleOp = roleRepository.findByAuthority("USER");
    Set<Role> roles = new HashSet<>();
    roles.add(roleOp.get());

    RestaurantUser restaurantUser = new RestaurantUser();
    restaurantUser.setUsername(registerRestaurantUserDto.getUsername());
    restaurantUser.setPassword(passwordEncoder.encode(registerRestaurantUserDto.getPassword()));
    restaurantUser.setAuthorities(roles);
    restaurantUser.setCreatedAt(LocalDateTime.now());

    restaurantUserRepository.save(restaurantUser);

    // TODO: create mapper to map between RestaurantUser and RestaurantUserDto
    RestaurantUserDto restaurantUserDto = new RestaurantUserDto();
    restaurantUserDto.setId(restaurantUser.getId());
    restaurantUserDto.setUsername(restaurantUser.getUsername());
    restaurantUserDto.setCreatedAt(restaurantUser.getCreatedAt());

    return restaurantUserDto;
  }

}

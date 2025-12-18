package com.sherwin.restaurant_review_platform.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.domain.entities.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantUserServiceImpl implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    System.out.println("In the user details service");

    if (!username.equals("Sherwin")) {
      throw new UsernameNotFoundException("Not Sherwin");
    }

    Set<Role> roles = new HashSet<>();
    Role role = new Role();
    role.setAuthority("USER");
    roles.add(role);

    RestaurantUser restaurantUser = new RestaurantUser();
    restaurantUser.setUsername("Sherwin");
    restaurantUser.setPassword(passwordEncoder.encode("password"));
    restaurantUser.setAuthorities(roles);

    return restaurantUser;
  }

}

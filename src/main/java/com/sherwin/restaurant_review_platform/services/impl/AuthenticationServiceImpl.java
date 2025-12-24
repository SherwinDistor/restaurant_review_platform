package com.sherwin.restaurant_review_platform.services.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sherwin.restaurant_review_platform.domain.dtos.LoginRequestDto;
import com.sherwin.restaurant_review_platform.domain.dtos.LoginResponseDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RegisterRestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.domain.entities.Role;
import com.sherwin.restaurant_review_platform.mappers.RestaurantUserMapper;
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
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final RestaurantUserMapper restaurantUserMapper;

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
    restaurantUser.setFirstName(registerRestaurantUserDto.getFirstName());
    restaurantUser.setLastName(registerRestaurantUserDto.getLastName());
    restaurantUser.setUsername(registerRestaurantUserDto.getUsername());
    restaurantUser.setPassword(passwordEncoder.encode(registerRestaurantUserDto.getPassword()));
    restaurantUser.setAuthorities(roles);
    restaurantUser.setCreatedAt(LocalDateTime.now());

    restaurantUserRepository.save(restaurantUser);

    RestaurantUserDto restaurantUserDto = restaurantUserMapper.toDto(restaurantUser);

    return restaurantUserDto;
  }

  @Override
  public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {

    try {

      Authentication auth = authenticationManager
          .authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequestDto.getUsername(),
                  loginRequestDto.getPassword()));

      String jwt = tokenService.generateJwt(auth);

      RestaurantUser restaurantUser = restaurantUserRepository
          .findByUsername(loginRequestDto.getUsername()).get();

      RestaurantUserDto restaurantUserDto = restaurantUserMapper.toDto(restaurantUser);

      LoginResponseDto loginResponseDto = new LoginResponseDto();
      loginResponseDto.setRestaurantUserDto(restaurantUserDto);
      loginResponseDto.setJwt(jwt);

      return loginResponseDto;

    } catch (Exception e) {
      // TODO: handle exceptions better for user login
      throw new RuntimeException("Unable to log in user: " + e);
    }

  }

}

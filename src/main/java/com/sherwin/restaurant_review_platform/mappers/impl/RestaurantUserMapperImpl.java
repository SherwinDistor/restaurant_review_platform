package com.sherwin.restaurant_review_platform.mappers.impl;

import org.springframework.stereotype.Component;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.mappers.RestaurantUserMapper;

@Component
public class RestaurantUserMapperImpl implements RestaurantUserMapper {

  @Override
  public RestaurantUserDto toDto(RestaurantUser restaurantUser) {

    RestaurantUserDto restaurantUserDto = new RestaurantUserDto();
    restaurantUserDto.setId(restaurantUser.getId());
    restaurantUserDto.setFirstName(restaurantUser.getFirstName());
    restaurantUserDto.setLastName(restaurantUser.getLastName());
    restaurantUserDto.setUsername(restaurantUser.getUsername());
    restaurantUserDto.setCreatedAt(restaurantUser.getCreatedAt());

    return restaurantUserDto;
  }

}

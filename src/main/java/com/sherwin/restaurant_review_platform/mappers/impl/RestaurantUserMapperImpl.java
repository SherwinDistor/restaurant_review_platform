package com.sherwin.restaurant_review_platform.mappers.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantUserDto;
import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.domain.entities.Review;
import com.sherwin.restaurant_review_platform.mappers.RestaurantUserMapper;
import com.sherwin.restaurant_review_platform.mappers.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestaurantUserMapperImpl implements RestaurantUserMapper {

  private final ReviewMapper reviewMapper;

  @Override
  public RestaurantUserDto toDto(RestaurantUser restaurantUser) {

    RestaurantUserDto restaurantUserDto = new RestaurantUserDto();
    restaurantUserDto.setId(restaurantUser.getId());
    restaurantUserDto.setFirstName(restaurantUser.getFirstName());
    restaurantUserDto.setLastName(restaurantUser.getLastName());
    restaurantUserDto.setUsername(restaurantUser.getUsername());
    restaurantUserDto.setCreatedAt(restaurantUser.getCreatedAt());

    List<Review> reviews = restaurantUser.getReviews();
    List<ReviewDto> reviewDtos = reviewMapper.toListDto(reviews);

    restaurantUserDto.setReviews(reviewDtos);

    return restaurantUserDto;
  }

}

package com.sherwin.restaurant_review_platform.mappers.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.domain.entities.Review;
import com.sherwin.restaurant_review_platform.mappers.RestaurantMapper;
import com.sherwin.restaurant_review_platform.mappers.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestaurantMapperImpl implements RestaurantMapper {

    private final ReviewMapper reviewMapper;

    @Override
    public RestaurantDto toDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setCuisineType(restaurant.getCuisineType());
        restaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDto.setAverageRating(restaurant.getAverageRating());

        List<Review> reviews = restaurant.getReviews();
        List<ReviewDto> reviewDtos = reviews
                .stream()
                .map(reviewMapper::toDto)
                .toList();

        restaurantDto.setReviews(reviewDtos);

        return restaurantDto;
    }

}

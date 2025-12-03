package com.sherwin.restaurant_review_platform.mappers.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sherwin.restaurant_review_platform.domain.dtos.PhotoDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.domain.entities.Photo;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.domain.entities.Review;
import com.sherwin.restaurant_review_platform.mappers.PhotoMapper;
import com.sherwin.restaurant_review_platform.mappers.RestaurantMapper;
import com.sherwin.restaurant_review_platform.mappers.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestaurantMapperImpl implements RestaurantMapper {

    private final ReviewMapper reviewMapper;
    private final PhotoMapper photoMapper;

    @Override
    public RestaurantDto toDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setCuisineType(restaurant.getCuisineType());
        restaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDto.setAverageRating(restaurant.getAverageRating());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setOperatingHours(restaurant.getOperatingHours());

        List<Review> reviews = restaurant.getReviews();
        List<ReviewDto> reviewDtos = reviewMapper.toListDto(reviews);

        restaurantDto.setReviews(reviewDtos);

        List<Photo> photos = restaurant.getPhotos();
        List<PhotoDto> photoDtos = photoMapper.toListDto(photos);

        restaurantDto.setPhotos(photoDtos);

        return restaurantDto;
    }

    @Override
    public List<RestaurantDto> toListDto(List<Restaurant> restaurants) {
        return restaurants
                .stream()
                .map(restaurant -> toDto(restaurant))
                .toList();
    }

}

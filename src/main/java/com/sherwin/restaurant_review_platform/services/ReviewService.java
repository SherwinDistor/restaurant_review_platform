package com.sherwin.restaurant_review_platform.services;

import java.util.List;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateReviewForRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;

public interface ReviewService {
    List<ReviewDto> getAllReviewsByRestaurant(Integer restaurantId);

    ReviewDto createReviewForRestaurant(Integer restaurantId,
            CreateReviewForRestaurantDto createReviewForRestaurantDto);
}

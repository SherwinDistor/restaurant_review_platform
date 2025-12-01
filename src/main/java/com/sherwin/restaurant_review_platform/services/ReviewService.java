package com.sherwin.restaurant_review_platform.services;

import java.util.List;
import java.util.UUID;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateReviewForRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;

public interface ReviewService {
    List<ReviewDto> getAllReviewsByRestaurant(UUID restaurantId);

    ReviewDto createReviewForRestaurant(UUID restaurantId,
            CreateReviewForRestaurantDto createReviewForRestaurantDto);

    ReviewDto getReviewById(UUID reviewId);

    ReviewDto updateReviewForRestaurant(
            UUID restaurantId,
            UUID reviewId,
            CreateReviewForRestaurantDto createReviewForRestaurantDto);

    void deleteReviewForRestaurant(UUID restaurantId, UUID reviewId);
}

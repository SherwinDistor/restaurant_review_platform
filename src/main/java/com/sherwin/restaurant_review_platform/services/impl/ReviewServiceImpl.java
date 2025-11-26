package com.sherwin.restaurant_review_platform.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateReviewForRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.domain.entities.Review;
import com.sherwin.restaurant_review_platform.mappers.ReviewMapper;
import com.sherwin.restaurant_review_platform.repositories.RestaurantRepository;
import com.sherwin.restaurant_review_platform.repositories.ReviewRepository;
import com.sherwin.restaurant_review_platform.services.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getAllReviewsByRestaurant(Integer restaurantId) {
        Optional<Restaurant> restaurantOp = restaurantRepository.findById(restaurantId);
        if (restaurantOp.isEmpty()) {
            throw new RuntimeException("Restaurant Not Found");
        }

        List<Review> restaurantReviews = reviewRepository.findByRestaurant(restaurantOp.get());

        List<ReviewDto> restaurantReviewDtos = restaurantReviews.stream().map(reviewMapper::toDto).toList();

        return restaurantReviewDtos;
    }

    @Override
    public ReviewDto createReviewForRestaurant(Integer restaurantId,
            CreateReviewForRestaurantDto createReviewForRestaurantDto) {

        Optional<Restaurant> restaurantOp = restaurantRepository.findById(restaurantId);
        if (restaurantOp.isEmpty()) {
            throw new RuntimeException("Restaurant Not Found");
        }

        Review newReview = new Review();
        newReview.setTitle(createReviewForRestaurantDto.getTitle());
        newReview.setContent(createReviewForRestaurantDto.getContent());
        newReview.setRating(createReviewForRestaurantDto.getRating());
        newReview.setCreatedAt(LocalDateTime.now());
        newReview.setRestaurant(restaurantOp.get());

        reviewRepository.save(newReview);

        return reviewMapper.toDto(newReview);
    }

}

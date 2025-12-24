package com.sherwin.restaurant_review_platform.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateReviewForRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;
import com.sherwin.restaurant_review_platform.domain.entities.Review;
import com.sherwin.restaurant_review_platform.exceptions.RestaurantException;
import com.sherwin.restaurant_review_platform.exceptions.ReviewException;
import com.sherwin.restaurant_review_platform.mappers.ReviewMapper;
import com.sherwin.restaurant_review_platform.repositories.RestaurantRepository;
import com.sherwin.restaurant_review_platform.repositories.RestaurantUserRepository;
import com.sherwin.restaurant_review_platform.repositories.ReviewRepository;
import com.sherwin.restaurant_review_platform.services.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final RestaurantRepository restaurantRepository;
  private final ReviewMapper reviewMapper;
  private final RestaurantUserRepository restaurantUserRepository;

  @Override
  public List<ReviewDto> getAllReviewsByRestaurant(UUID restaurantId) {
    Optional<Restaurant> restaurantOp = restaurantRepository.findById(restaurantId);
    if (restaurantOp.isEmpty()) {
      throw new RestaurantException("Restaurant Not Found");
    }

    List<Review> restaurantReviews = reviewRepository.findByRestaurant(restaurantOp.get());

    System.out.println(reviewRepository.findAverageRatingByRestaurant(restaurantOp.get()));

    List<ReviewDto> restaurantReviewDtos = reviewMapper.toListDto(restaurantReviews);

    return restaurantReviewDtos;
  }

  @Override
  public ReviewDto createReviewForRestaurant(
      UUID restaurantId,
      CreateReviewForRestaurantDto createReviewForRestaurantDto,
      String username) {

    Optional<Restaurant> restaurantOp = restaurantRepository.findById(restaurantId);
    if (restaurantOp.isEmpty()) {
      throw new RestaurantException("Restaurant Not Found");
    }

    Optional<RestaurantUser> userOp = restaurantUserRepository.findByUsername(username);
    if (userOp.isEmpty()) {
      throw new RuntimeException("User Not Found");
    }

    Restaurant restaurant = restaurantOp.get();
    RestaurantUser restaurantUser = userOp.get();

    Review newReview = new Review();
    newReview.setTitle(createReviewForRestaurantDto.getTitle());
    newReview.setContent(createReviewForRestaurantDto.getContent());
    newReview.setRating(createReviewForRestaurantDto.getRating());
    newReview.setCreatedAt(LocalDateTime.now());
    newReview.setUpdatedAt(LocalDateTime.now());
    newReview.setRestaurant(restaurant);
    newReview.setRestaurantUser(restaurantUser);

    reviewRepository.save(newReview);

    // Update restaurant's average review
    updateRestaurantAverageRating(restaurant);

    return reviewMapper.toDto(newReview);
  }

  private void updateRestaurantAverageRating(Restaurant restaurant) {
    Float averageRating = reviewRepository.findAverageRatingByRestaurant(restaurant);
    restaurant.setAverageRating(averageRating);
    restaurantRepository.save(restaurant);
  }

  @Override
  public ReviewDto getReviewById(UUID reviewId) {
    Optional<Review> reviewOp = reviewRepository.findById(reviewId);
    if (reviewOp.isEmpty()) {
      throw new ReviewException("Review Not Found");
    }

    return reviewMapper.toDto(reviewOp.get());
  }

  @Override
  public ReviewDto updateReviewForRestaurant(UUID restaurantId, UUID reviewId,
      CreateReviewForRestaurantDto createReviewForRestaurantDto) {
    Optional<Restaurant> restaurantOp = restaurantRepository.findById(restaurantId);
    Optional<Review> reviewOp = reviewRepository.findById(reviewId);
    if (restaurantOp.isEmpty()) {
      throw new RestaurantException("Restaurant Not Found");
    } else if (reviewOp.isEmpty()) {
      throw new ReviewException("Review Not Found");
    }

    Review review = reviewOp.get();
    review.setTitle(createReviewForRestaurantDto.getTitle());
    review.setContent(createReviewForRestaurantDto.getContent());
    review.setRating(createReviewForRestaurantDto.getRating());
    review.setUpdatedAt(LocalDateTime.now());

    reviewRepository.save(review);
    updateRestaurantAverageRating(restaurantOp.get());

    return reviewMapper.toDto(review);
  }

  @Override
  public void deleteReviewForRestaurant(UUID restaurantId, UUID reviewId) {
    Optional<Restaurant> restaurantOp = restaurantRepository.findById(restaurantId);
    if (restaurantOp.isEmpty()) {
      throw new RestaurantException("Restaurant Not Found");
    }

    reviewRepository.deleteById(reviewId);
    updateRestaurantAverageRating(restaurantOp.get());
  }

}

package com.sherwin.restaurant_review_platform.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateReviewForRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.services.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsByRestaurant(@PathVariable Integer restaurantId) {
        List<ReviewDto> allReviewsByRestaurant = reviewService.getAllReviewsByRestaurant(restaurantId);

        return new ResponseEntity<>(allReviewsByRestaurant, HttpStatus.OK);
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<ReviewDto> createNewReviewForRestaurant(
            @PathVariable Integer restaurantId,
            @RequestBody CreateReviewForRestaurantDto createReviewForRestaurantDto) {
        ReviewDto reviewForRestaurant = reviewService.createReviewForRestaurant(restaurantId,
                createReviewForRestaurantDto);

        return new ResponseEntity<>(reviewForRestaurant, HttpStatus.CREATED);
    }

}

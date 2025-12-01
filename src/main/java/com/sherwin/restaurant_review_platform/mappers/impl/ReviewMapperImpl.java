package com.sherwin.restaurant_review_platform.mappers.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.domain.entities.Review;
import com.sherwin.restaurant_review_platform.mappers.ReviewMapper;

@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setRating(review.getRating());
        reviewDto.setUpdatedAt(review.getUpdatedAt());

        return reviewDto;
    }

    @Override
    public List<ReviewDto> toListDto(List<Review> reviews) {
        return reviews.stream().map(review -> toDto(review)).toList();
    }

}

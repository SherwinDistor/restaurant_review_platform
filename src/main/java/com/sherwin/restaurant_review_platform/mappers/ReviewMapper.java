package com.sherwin.restaurant_review_platform.mappers;

import java.util.List;

import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.domain.entities.Review;

public interface ReviewMapper {
    ReviewDto toDto(Review review);

    List<ReviewDto> toListDto(List<Review> reviews);
}

package com.sherwin.restaurant_review_platform.mappers;

import com.sherwin.restaurant_review_platform.domain.dtos.ReviewDto;
import com.sherwin.restaurant_review_platform.domain.entities.Review;

public interface ReviewMapper {
    ReviewDto toDto(Review review);
}

package com.sherwin.restaurant_review_platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Integer id;
    private String title;
    private String content;
    private Integer rating;
}

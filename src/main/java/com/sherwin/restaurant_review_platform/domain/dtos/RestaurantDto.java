package com.sherwin.restaurant_review_platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
    private Integer id;
    private String name;
    private String cuisineType;
    private String phoneNumber;
    private Float averageRating;
}

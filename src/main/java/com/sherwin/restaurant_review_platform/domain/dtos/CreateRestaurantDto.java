package com.sherwin.restaurant_review_platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantDto {
    private String name;
    private String cuisineType;
    private String phoneNumber;
}

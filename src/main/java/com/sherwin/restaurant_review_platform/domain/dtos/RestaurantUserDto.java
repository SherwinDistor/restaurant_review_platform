package com.sherwin.restaurant_review_platform.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantUserDto {
  private UUID id;
  private String username;
  private LocalDateTime createdAt;
}

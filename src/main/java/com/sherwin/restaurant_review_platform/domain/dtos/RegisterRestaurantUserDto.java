package com.sherwin.restaurant_review_platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRestaurantUserDto {
  private String firstName;
  private String lastName;
  private String username;
  private String password;
}

package com.sherwin.restaurant_review_platform.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

  @Query("SELECT r FROM Restaurant r WHERE " +
      "LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
      "LOWER(r.cuisineType) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
      "LOWER(r.address.streetName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
      "LOWER(r.address.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
      "LOWER(r.address.state) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
      "LOWER(r.address.zipCode) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  List<Restaurant> searchRestaurant(String keyword);

}

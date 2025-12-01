package com.sherwin.restaurant_review_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.domain.entities.Review;
import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByRestaurant(Restaurant restaurant);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.restaurant = :restaurant")
    Float findAverageRatingByRestaurant(Restaurant restaurant);
}

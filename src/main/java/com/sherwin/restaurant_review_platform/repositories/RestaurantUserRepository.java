package com.sherwin.restaurant_review_platform.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherwin.restaurant_review_platform.domain.entities.RestaurantUser;

@Repository
public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, UUID> {

  Optional<RestaurantUser> findByUsername(String username);
}

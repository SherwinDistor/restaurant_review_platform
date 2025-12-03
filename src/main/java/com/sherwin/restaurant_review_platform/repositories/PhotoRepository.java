package com.sherwin.restaurant_review_platform.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherwin.restaurant_review_platform.domain.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {

}

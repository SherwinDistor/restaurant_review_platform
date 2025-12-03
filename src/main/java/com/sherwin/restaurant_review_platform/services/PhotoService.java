package com.sherwin.restaurant_review_platform.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.sherwin.restaurant_review_platform.domain.dtos.PhotoDto;

public interface PhotoService {
  PhotoDto uploadPhoto(MultipartFile file, UUID restaurantId);

  Optional<Resource> getPhotoAsResource(String id);
}

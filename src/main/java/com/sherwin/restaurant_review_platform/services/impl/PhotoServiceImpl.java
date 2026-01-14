package com.sherwin.restaurant_review_platform.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sherwin.restaurant_review_platform.domain.dtos.PhotoDto;
import com.sherwin.restaurant_review_platform.domain.entities.Photo;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.exceptions.RestaurantException;
import com.sherwin.restaurant_review_platform.exceptions.StorageException;
import com.sherwin.restaurant_review_platform.mappers.PhotoMapper;
import com.sherwin.restaurant_review_platform.repositories.PhotoRepository;
import com.sherwin.restaurant_review_platform.repositories.RestaurantRepository;
import com.sherwin.restaurant_review_platform.services.PhotoService;
import com.sherwin.restaurant_review_platform.services.StorageService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

  private final StorageService storageService;
  private final PhotoMapper photoMapper;
  private final RestaurantRepository restaurantRepository;
  private final PhotoRepository photoRepository;

  @Override
  public PhotoDto uploadPhoto(MultipartFile file, UUID restaurantId) {
    String photoId = UUID.randomUUID().toString();
    String photoUrl = storageService.store(file, photoId);

    Optional<Restaurant> restaurantOp = restaurantRepository.findById(restaurantId);
    if (restaurantOp.isEmpty()) {
      throw new RestaurantException("Restaurant Not Found");
    }

    Restaurant restaurant = restaurantOp.get();

    Photo newPhoto = new Photo();
    newPhoto.setUrl(photoUrl);
    newPhoto.setUploadedAt(LocalDateTime.now());
    newPhoto.setRestaurant(restaurant);

    photoRepository.save(newPhoto);

    return photoMapper.toDto(newPhoto);
  }

  @Override
  public Resource getPhotoAsResource(String filename) {
    Optional<Resource> resourceOp = storageService.loadAsResource(filename);
    if (resourceOp.isEmpty()) {
      throw new StorageException("Image could not be found");
    }

    return resourceOp.get();
  }

  @Override
  public void deletePhoto(UUID photoId) {
    Optional<Photo> photoOp = photoRepository.findById(photoId);
    if (photoOp.isEmpty()) {
      throw new StorageException("Photo not found");
    }

    Photo photo = photoOp.get();

    storageService.delete(photo.getUrl());
    photoRepository.delete(photo);
  }

}

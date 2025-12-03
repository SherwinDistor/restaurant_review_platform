package com.sherwin.restaurant_review_platform.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sherwin.restaurant_review_platform.domain.dtos.PhotoDto;
import com.sherwin.restaurant_review_platform.services.PhotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/photo")
public class PhotoController {

  private final PhotoService photoService;

  @PostMapping("/{restaurantId}")
  public ResponseEntity<PhotoDto> uploadPhoto(@RequestParam("file") MultipartFile file,
      @PathVariable UUID restaurantId) {
    System.out.println("Controller hit");

    PhotoDto uploadPhotoDto = photoService.uploadPhoto(file, restaurantId);

    System.out.println("Called photo service");

    return new ResponseEntity<>(uploadPhotoDto, HttpStatus.CREATED);
  }

}

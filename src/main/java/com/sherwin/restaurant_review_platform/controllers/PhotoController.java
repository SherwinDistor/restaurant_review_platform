package com.sherwin.restaurant_review_platform.controllers;

import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sherwin.restaurant_review_platform.domain.dtos.PhotoDto;
import com.sherwin.restaurant_review_platform.services.PhotoService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
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

  @GetMapping("/{photoFilename:.+}")
  public ResponseEntity<Resource> getPhoto(@PathVariable String photoFilename) {
    Resource photo = photoService.getPhotoAsResource(photoFilename);
    MediaType contentType = MediaTypeFactory.getMediaType(photo)
        .orElse(MediaType.APPLICATION_OCTET_STREAM);

    return ResponseEntity.ok()
        .contentType(contentType)
        .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
        .body(photo);
  }

}

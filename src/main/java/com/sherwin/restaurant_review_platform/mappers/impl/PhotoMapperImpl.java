package com.sherwin.restaurant_review_platform.mappers.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sherwin.restaurant_review_platform.domain.dtos.PhotoDto;
import com.sherwin.restaurant_review_platform.domain.entities.Photo;
import com.sherwin.restaurant_review_platform.mappers.PhotoMapper;

@Component
public class PhotoMapperImpl implements PhotoMapper {

  @Override
  public PhotoDto toDto(Photo photo) {
    PhotoDto newPhotoDto = new PhotoDto();
    newPhotoDto.setUrl(photo.getUrl());
    newPhotoDto.setUploadedAt(photo.getUploadedAt());
    return newPhotoDto;
  }

  @Override
  public List<PhotoDto> toListDto(List<Photo> photos) {
    return photos.stream().map(photo -> toDto(photo)).toList();
  }

}

package com.sherwin.restaurant_review_platform.mappers;

import java.util.List;

import com.sherwin.restaurant_review_platform.domain.dtos.PhotoDto;
import com.sherwin.restaurant_review_platform.domain.entities.Photo;

public interface PhotoMapper {
  PhotoDto toDto(Photo photo);

  List<PhotoDto> toListDto(List<Photo> photos);
}

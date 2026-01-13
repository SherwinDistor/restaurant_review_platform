package com.sherwin.restaurant_review_platform.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.sherwin.restaurant_review_platform.exceptions.StorageException;
import com.sherwin.restaurant_review_platform.services.StorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryStorageServiceImpl implements StorageService {

  private final Cloudinary cloudinary;

  @SuppressWarnings("rawtypes")
  @Override
  public String store(MultipartFile file, String filename) {
    try {
      Map<String, Object> options = new HashMap<>();
      options.put("public_id", filename);
      options.put("folder", "restaurant_photos");
      options.put("overwrite", true);

      Map upload = cloudinary.uploader().upload(file.getBytes(), options);

      return upload.get("secure_url").toString();

    } catch (Exception e) {
      throw new StorageException("Cloudinary upload failed", e);
    }
  }

  @Override
  public Optional<Resource> loadAsResource(String filename) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'loadAsResource'");
  }

  @Override
  public void delete(String filename) {
    try {
      String publicId = extractPublicId(filename);
      Map<String, Object> options = new HashMap<>();
      options.put("invalidate", true);

      cloudinary.uploader().destroy(publicId, options);
    } catch (IOException e) {
      throw new StorageException("Cloudinary deletion failed", e);
    }
  }

  private String extractPublicId(String url) {
    return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
  }

}

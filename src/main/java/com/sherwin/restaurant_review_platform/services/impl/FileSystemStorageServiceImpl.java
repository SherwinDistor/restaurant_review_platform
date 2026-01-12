package com.sherwin.restaurant_review_platform.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sherwin.restaurant_review_platform.exceptions.StorageException;
import com.sherwin.restaurant_review_platform.services.StorageService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemStorageServiceImpl implements StorageService {

  @Value("${app.storage.location:uploads}")
  private String storageLocation;

  private Path rootLocation;

  @PostConstruct
  public void init() {
    rootLocation = Paths.get(storageLocation);

    try {
      System.out.println("Init run, created folder");
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage location", e);
    }

  }

  @Override
  public String store(MultipartFile file, String filename) {
    System.out.println("File system store called...");

    try {

      if (file.isEmpty()) {
        throw new StorageException("Cannot save an empty file");
      }

      String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
      String finalFilename = filename + "." + extension;

      System.out.println("Grab extension and create final file name");

      Path destinationFile = rootLocation
          .resolve(Paths.get(finalFilename))
          .normalize()
          .toAbsolutePath();

      System.out.println("Resolve run, to get path of destination file");

      if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
        throw new StorageException("Cannot store file outside specified directory");
      }

      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
      }

      System.out.println("Try to save file");

      return finalFilename;

    } catch (IOException e) {
      throw new StorageException("Failed to store file", e);
    }
  }

  @Override
  public Optional<Resource> loadAsResource(String filename) {
    try {
      Path filePath = rootLocation.resolve(filename);
      Resource resource = new UrlResource(filePath.toUri());

      if (resource.exists() || resource.isReadable()) {
        return Optional.of(resource);
      } else {
        return Optional.empty();
      }
    } catch (MalformedURLException e) {
      log.warn("Could not read file: " + filename, e);
      return Optional.empty();
    }
  }

  @Override
  public void delete(String filename) {
    try {
      Path filePath = rootLocation.resolve(filename);
      Files.deleteIfExists(filePath);
      log.info("Deleted file: {}", filename);
    } catch (IOException e) {
      throw new StorageException("Could not delete file: " + filename, e);
    }
  }

}

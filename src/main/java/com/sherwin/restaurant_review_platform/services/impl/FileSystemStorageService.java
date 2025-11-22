package com.sherwin.restaurant_review_platform.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sherwin.restaurant_review_platform.exceptions.StorageException;
import com.sherwin.restaurant_review_platform.services.StorageService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

    @Value("${app.storage.location:uploads}")
    private String storageLocation;

    private Path rootLocation;

    // After object is constructed, this method will run
    @PostConstruct
    public void init() {
        rootLocation = Path.of(storageLocation);

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file, String filename) {
        try {

            if (file.isEmpty()) {
                throw new StorageException("Cannot save an empty file");
            }

            @Nullable
            String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String finalFilename = filename + "." + filenameExtension;

            Path destinationFile = rootLocation
                    .resolve(Path.of(finalFilename))
                    .normalize()
                    .toAbsolutePath();

            // Basic security check of uploaded file
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside specified directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return finalFilename;
        } catch (Exception e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public Optional<Resource> loadAsResource(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadAsResource'");
    }

}

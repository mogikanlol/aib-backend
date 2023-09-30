package com.mogikanlol.aib.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageSaver {

    private final MinioClient minioClient;

    public String save(MultipartFile image) {
        String imageName = generateImageName(image);

        PutObjectArgs args = null;
        try {
            args = PutObjectArgs.builder()
                    .bucket("my-first-bucket")
                    .object(imageName)
                    .stream(image.getInputStream(), image.getSize(), -1)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            minioClient.putObject(args);
        } catch (ErrorResponseException | InsufficientDataException | InvalidKeyException | InvalidResponseException |
                 IOException | NoSuchAlgorithmException | InternalException | ServerException | XmlParserException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return imageName;
    }

    private String generateImageName(MultipartFile file) {
        String extension = getFileExtension(file);
        return UUID.randomUUID().toString() + extension;
    }

    private String getFileExtension(MultipartFile file) {
        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }
}

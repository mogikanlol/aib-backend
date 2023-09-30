package com.mogikanlol.aib.resource;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageResource {

    private final MinioClient minioClient;

    @GetMapping("/{title}")
    public byte[] getImage(@PathVariable("title") String title) throws Exception {

        GetObjectArgs args = GetObjectArgs.builder()
                .bucket("my-first-bucket")
                .object(title)
                .build();
        GetObjectResponse response = minioClient.getObject(args);

        return response.readAllBytes();
    }
}

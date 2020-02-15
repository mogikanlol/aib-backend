package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.configuration.ImagesProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageResource {

    private final ImagesProperties imagesProperties;

    @GetMapping("/{title}")
    public byte[] getImage(@PathVariable("title") String title) throws Exception {
        Path path = Paths.get(imagesProperties.getPathToFolder() + "/" + title);
        return Files.readAllBytes(path);
    }
}

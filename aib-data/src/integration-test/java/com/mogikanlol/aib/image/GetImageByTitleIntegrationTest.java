package com.mogikanlol.aib.image;

import com.mogikanlol.aib.AbstractIntegrationTest;
import com.mogikanlol.aib.configuration.ImagesProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetImageByTitleIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ImagesProperties imagesProperties;

    @Test
    void successPath() throws Exception {
        byte[] expectedBytes = Files.readAllBytes(Paths.get(imagesProperties.getPathToFolder() + "/eva.png"));

        ResponseEntity<byte[]> entity = restTemplate.getForEntity("http://localhost:" + port + "/images/eva.png", byte[].class);
        byte[] actualBytes = entity.getBody();

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertArrayEquals(expectedBytes, actualBytes);
    }

}

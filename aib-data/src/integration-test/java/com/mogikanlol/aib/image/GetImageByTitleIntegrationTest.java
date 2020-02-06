package com.mogikanlol.aib.image;

import com.mogikanlol.aib.IntegrationTest;
import com.mogikanlol.aib.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
public class GetImageByTitleIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void successPath() throws Exception {
        byte[] expectedBytes = TestUtils.readResourceAsBytes("images/eva.png");

        ResponseEntity<byte[]> entity = restTemplate.getForEntity("http://localhost:" + port + "/images/eva.png", byte[].class);
        byte[] actualBytes = entity.getBody();

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertArrayEquals(expectedBytes, actualBytes);
    }

}

package com.mogikanlol.aib.thread;

import com.mogikanlol.aib.IntegrationTest;
import com.mogikanlol.aib.TestUtils;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.repository.ThreadRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.JsonExpectationsHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
public class GetThreadByIdIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ThreadRepository threadRepository;

    private static JsonExpectationsHelper jsonExpectationsHelper;

    @BeforeAll
    static void setUp() {
        jsonExpectationsHelper = new JsonExpectationsHelper();
    }

    @BeforeEach
    void setData() {
        Thread evaThread = new Thread()
                .setId(1L)
                .setTitle("Neon Genesis Evangelion")
                .setImageUrl("http://localhost:8080/images/url")
                .setContent("content");

        threadRepository.save(evaThread);
    }

    @AfterEach
    void removeData() {
        threadRepository.deleteAll();
    }

    @Test
    void successPath() throws Exception {
        String expectedJson = TestUtils.readResourceAsString("thread/get-thread-by-id-200.json");

        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/threads/1", String.class);
        String actualJson = entity.getBody();

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }

}

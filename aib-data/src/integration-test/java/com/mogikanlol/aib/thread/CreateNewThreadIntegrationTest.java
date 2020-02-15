package com.mogikanlol.aib.thread;

import com.mogikanlol.aib.IntegrationTest;
import com.mogikanlol.aib.TestUtils;
import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.repository.BoardRepository;
import com.mogikanlol.aib.repository.ThreadRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Example;
import org.springframework.http.*;
import org.springframework.test.util.JsonExpectationsHelper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
public class CreateNewThreadIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    private static JsonExpectationsHelper jsonExpectationsHelper;

    @BeforeAll
    static void setUp() {
        jsonExpectationsHelper = new JsonExpectationsHelper();
    }

    @Test
    void successPath() throws Exception {
        String requestJson = TestUtils.readResourceAsString("thread/new-thread.json");
        String expectedJson = TestUtils.readResourceAsString("thread/post-new-thread-200.json");

        HttpHeaders entityHeaders = new HttpHeaders();
        entityHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> threadEntity = new HttpEntity<>(requestJson, entityHeaders);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
        map.add("thread", threadEntity);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, requestHeaders);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/threads", httpEntity, String.class);
        String actualJson = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThreadExistsInDB();
        // TODO: Figure out how to check id in json response
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);

        removeEntityFromDB();
    }

    private void assertThreadExistsInDB() {
        Thread thread = new Thread()
                .setTitle("Test title")
                .setContent("Test content");
        Example<Thread> example = Example.of(thread);
        assertTrue(threadRepository.exists(example));
    }

    private void removeEntityFromDB() {
        Thread thread = new Thread()
                .setTitle("Test title")
                .setContent("Test content");
        Example<Thread> example = Example.of(thread);
        Thread entity = threadRepository.findOne(example).orElse(null);
        if (entity != null) {
            threadRepository.delete(entity);
        }
    }
}

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.JsonExpectationsHelper;

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

    @BeforeEach
    void setData() {
        Board board = new Board()
                .setId("test")
                .setTitle("Test title");

        boardRepository.save(board);
    }

    @AfterEach
    void removeData() {
        boardRepository.deleteAll();
    }

    @Test
    void successPath() throws Exception {
        String requestJson = TestUtils.readResourceAsString("thread/new-thread.json");
        String expectedJson = TestUtils.readResourceAsString("thread/post-new-thread-200.json");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json;charset=UTF-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, httpHeaders);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/threads", httpEntity, String.class);
        String actualJson = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThreadExistsInDB();
        // TODO: Figure out how to check id in json response
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }

    private void assertThreadExistsInDB() {
        Board board = new Board()
                .setId("test")
                .setTitle("Test title");
        Thread thread = new Thread()
                .setTitle("Test title")
                .setContent("Test content")
                .setBoard(board);
        Example<Thread> example = Example.of(thread);
        assertTrue(threadRepository.exists(example));
    }
}

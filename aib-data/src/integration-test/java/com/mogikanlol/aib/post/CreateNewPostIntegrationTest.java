package com.mogikanlol.aib.post;

import com.mogikanlol.aib.AbstractIntegrationTest;
import com.mogikanlol.aib.TestUtils;
import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.repository.BoardRepository;
import com.mogikanlol.aib.repository.PostRepository;
import com.mogikanlol.aib.repository.ThreadRepository;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.util.JsonExpectationsHelper;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class CreateNewPostIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        Board board = new Board()
                .setId("a")
                .setTitle("Anime")
                .setGenre(Board.BoardGenre.JAPAN);
        boardRepository.save(board);

        Thread thread = new Thread()
                .setTitle("Neon Genesis Evangelion Thread")
                .setImageName("eva.png")
                .setContent("content")
                .setBoard(board);
        threadRepository.save(thread);
    }

    @Test
    void successPath() throws Exception {
        String requestJson = TestUtils.readResourceAsString("post/new-post.json");
        String expectedJson = TestUtils.readResourceAsString("post/post-new-post-200.json");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json;charset=UTF-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, httpHeaders);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/posts", httpEntity, String.class);
        String actualJson = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }
}

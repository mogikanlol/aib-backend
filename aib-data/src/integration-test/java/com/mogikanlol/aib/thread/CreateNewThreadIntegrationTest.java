package com.mogikanlol.aib.thread;

import com.mogikanlol.aib.AbstractIntegrationTest;
import com.mogikanlol.aib.TestUtils;
import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateNewThreadIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUpDatabase() {
        Board animeBoard = new Board()
                .setId("a")
                .setTitle("Anime")
                .setGenre(Board.BoardGenre.JAPAN);
        boardRepository.save(animeBoard);
    }

    @Test
    void successPath() throws Exception {
        String requestJson = TestUtils.readResourceAsString("thread/new-thread-request.json");
        String expectedJson = TestUtils.readResourceAsString("thread/post-new-thread-response-200.json");

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
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }

    @Test
    void boardNotFound() throws Exception {
        String requestJson = TestUtils.readResourceAsString("thread/new-thread-with-wrong-board-request.json");

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

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

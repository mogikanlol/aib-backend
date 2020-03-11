package com.mogikanlol.aib.post;

import com.mogikanlol.aib.AbstractIntegrationTest;
import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.domain.Post;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.repository.BoardRepository;
import com.mogikanlol.aib.repository.PostRepository;
import com.mogikanlol.aib.repository.ThreadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeletePostIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PostRepository postRepository;

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

        Post post = new Post()
                .setContent("content")
                .setThread(thread);
        postRepository.save(post);
    }

    @Test
    void successPath() {
        ResponseEntity<Long> response = restTemplate.exchange("http://localhost:" + port + "/posts/1", HttpMethod.DELETE, null, Long.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Long.valueOf(1L), response.getBody());
    }
}

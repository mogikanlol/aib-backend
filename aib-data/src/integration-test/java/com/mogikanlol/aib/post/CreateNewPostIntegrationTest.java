package com.mogikanlol.aib.post;

import com.mogikanlol.aib.IntegrationTest;
import com.mogikanlol.aib.TestUtils;
import com.mogikanlol.aib.domain.Post;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.repository.PostRepository;
import org.junit.jupiter.api.BeforeAll;
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
public class CreateNewPostIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    private static JsonExpectationsHelper jsonExpectationsHelper;

    @BeforeAll
    static void setUp() {
        jsonExpectationsHelper = new JsonExpectationsHelper();
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
        assertPostExistsInDB();
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);

        removeEntityFromDB();
    }

    private void assertPostExistsInDB() {
        Post post = new Post()
                .setContent("Test post content");
        Example<Post> example = Example.of(post);
        assertTrue(postRepository.exists(example));
    }

    private void removeEntityFromDB() {
        Post post = new Post()
                .setContent("Test post content");
        Example<Post> example = Example.of(post);
        Post entity = postRepository.findOne(example).orElse(null);
        if (entity != null) {
            postRepository.delete(entity);
        }
    }
}

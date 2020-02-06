package com.mogikanlol.aib.board;

import com.mogikanlol.aib.IntegrationTest;
import com.mogikanlol.aib.TestUtils;
import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.repository.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.JsonExpectationsHelper;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
public class GetAllBoardsIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    private static JsonExpectationsHelper jsonExpectationsHelper;

    @BeforeAll
    static void setUp() {
        jsonExpectationsHelper = new JsonExpectationsHelper();
    }

    @BeforeEach
    void setData() {
        Board japanBoard = new Board()
                .setId("jp")
                .setGenre(Board.BoardGenre.JAPAN)
                .setTitle("Japan");
        Board mangaBoard = new Board()
                .setId("mng")
                .setGenre(Board.BoardGenre.JAPAN)
                .setTitle("Manga");

        boardRepository.saveAll(Arrays.asList(japanBoard, mangaBoard));
    }

    @AfterEach
    void removeData() {
        boardRepository.deleteAll();
    }

    @Test
    void successPath() throws Exception {
        String expectedJson = TestUtils.readResourceAsString("board/get-boards-200.json");

        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/boards", String.class);
        String actualJson = entity.getBody();

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }

}

package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.dto.BoardDto;
import com.mogikanlol.aib.dto.BoardShortDto;
import com.mogikanlol.aib.service.BoardService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardResource {

    private final BoardService boardService;
    private final MapperFacade mapperFacade;

    @GetMapping
    public List<BoardShortDto> getAll() {
        return boardService.getAll().stream()
                .map(b -> mapperFacade.map(b, BoardShortDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BoardDto getById(@PathVariable("id") String id) {
        Board board = boardService.getById(id);
        return mapperFacade.map(board, BoardDto.class);
    }
}

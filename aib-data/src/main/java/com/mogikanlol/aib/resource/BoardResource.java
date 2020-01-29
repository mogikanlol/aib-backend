package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.dto.BoardDto;
import com.mogikanlol.aib.repository.BoardRepository;
import com.mogikanlol.aib.service.BoardService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BoardResource {

    private final BoardService boardService;
    private final MapperFacade mapperFacade;

    @GetMapping("/boards")
    public List<BoardDto> getAll() {
        return boardService.getAll().stream()
                .map(b -> mapperFacade.map(b, BoardDto.class))
                .collect(Collectors.toList());
    }

}

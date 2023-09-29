package com.mogikanlol.aib.mapper;

import com.mogikanlol.aib.domain.Board;
import com.mogikanlol.aib.dto.BoardDto;
import com.mogikanlol.aib.dto.BoardShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardMapper {

    private final ThreadMapper threadMapper;

    public BoardDto map(Board entity) {
        return new BoardDto()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setGenre(entity.getGenre())
                .setThreads(threadMapper.map(entity.getThreads()));
    }

    public BoardShortDto mapToShort(Board entity) {
        return new BoardShortDto()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setGenre(entity.getGenre());

    }

}

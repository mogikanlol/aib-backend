package com.mogikanlol.aib.dto;

import com.mogikanlol.aib.domain.Board;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class BoardDto {

    private String id;

    private String title;

    private Board.BoardGenre genre;

    private List<ThreadDto> threads = new ArrayList<>();

}

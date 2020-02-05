package com.mogikanlol.aib.dto;

import com.mogikanlol.aib.domain.Board;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BoardShortDto {

    private String id;

    private String title;

    private Board.BoardGenre genre;

}

package com.mogikanlol.aib.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
@Accessors(chain = true)
public class Board {

    @Id
    private String id;

    private String title;

    @Enumerated(EnumType.STRING)
    private BoardGenre genre;

    public enum BoardGenre {
        JAPAN,
        INTERESTS,
        MOVIES,
        MUSIC,
        CREATIVE,
        GAMES
    }
}

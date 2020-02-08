package com.mogikanlol.aib.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Board {

    @Id
    private String id;

    private String title;

    @Enumerated(EnumType.STRING)
    private BoardGenre genre;

    @OneToMany(mappedBy = "board")
    private List<Thread> threads = new ArrayList<>();

    public enum BoardGenre {
        JAPAN,
        INTERESTS,
        MOVIES,
        MUSIC,
        CREATIVE,
        GAMES
    }
}

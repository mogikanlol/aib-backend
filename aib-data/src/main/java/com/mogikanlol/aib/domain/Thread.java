package com.mogikanlol.aib.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Thread {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String imageUrl;

    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}

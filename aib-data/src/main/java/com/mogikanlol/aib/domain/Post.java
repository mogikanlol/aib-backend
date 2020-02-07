package com.mogikanlol.aib.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;
}

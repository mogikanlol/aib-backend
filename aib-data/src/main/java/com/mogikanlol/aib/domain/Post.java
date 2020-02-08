package com.mogikanlol.aib.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_generator")
    @SequenceGenerator(name = "post_id_generator", sequenceName = "post_id_seq", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thread_id")
    private Thread thread;
}

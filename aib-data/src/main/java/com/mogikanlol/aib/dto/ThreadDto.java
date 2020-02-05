package com.mogikanlol.aib.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThreadDto {

    private Long id;

    private String title;

    private String imageUrl;

    private String content;

}

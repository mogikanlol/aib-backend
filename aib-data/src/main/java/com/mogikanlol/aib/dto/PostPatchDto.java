package com.mogikanlol.aib.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostPatchDto {

    private String content;

}
